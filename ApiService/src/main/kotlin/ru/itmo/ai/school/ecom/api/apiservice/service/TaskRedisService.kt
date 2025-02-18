package ru.itmo.ai.school.ecom.api.apiservice.service

import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.model.Task
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class TaskRedisService(
    private val redisTemplate: ReactiveRedisTemplate<String, Task>
) {

    private val givenCountMap = ConcurrentHashMap<UUID, Int>()

    fun getTask(taskTypeId: UUID): Mono<Task> {
        return redisTemplate.opsForList().range(taskTypeId.toString(), 0, -1)
            .filter { task ->
                val givenCount = givenCountMap[task.id] ?: 0
                givenCount < task.overlapCoefficient
            }
            .next()
            .flatMap { task ->
                incrementGivenCount(task).thenReturn(task)
            }
    }

    fun saveTask(task: Task): Mono<Long> {
        return redisTemplate.opsForList().rightPush(task.taskTypeName, task)
            .flatMap { _ -> updateGivenCount(task) }
    }

    private fun incrementGivenCount(task: Task): Mono<Boolean> {
        val newGivenCount = (givenCountMap[task.id] ?: 0) + 1
        givenCountMap[task.id] = newGivenCount
        return Mono.just(true)
    }

    private fun updateGivenCount(task: Task): Mono<Long> {
        val newGivenCount = task.givenCount + 1
        givenCountMap[task.id] = newGivenCount
        return Mono.just(newGivenCount.toLong())
    }
}




