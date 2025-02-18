package ru.itmo.ai.school.ecom.api.apiservice.service

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.model.Task
import java.util.*

@Service
class TaskRedisService(
    private val redisTemplate: ReactiveRedisTemplate<String, Task>
) {

    fun getTask(taskTypeId: UUID): Mono<Task> {
        return redisTemplate.opsForList().range(taskTypeId.toString(), 0, -1)
            .filter {
                println(it)
                it.givenCount < it.overlapCoefficient
            }
            .take(1)
            .next()
            .flatMap { task ->
                incrementGivenCount(task).thenReturn(task)
            }
    }

    fun saveTask(task: Task): Mono<Long> {
        return redisTemplate.opsForList().rightPush(task.taskTypeName, task)
    }

    private fun incrementGivenCount(task: Task): Mono<Boolean> {
        return saveTask(task.copy(givenCount = task.givenCount + 1)).map { true }
    }
}


