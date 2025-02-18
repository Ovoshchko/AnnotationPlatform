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

    private val givenCountMap = ConcurrentHashMap<UUID, Pair<Int, MutableSet<String>>>()

    fun getTask(taskTypeId: UUID, user: String): Mono<Task> {
        return redisTemplate.opsForList().range(taskTypeId.toString(), 0, -1)
            .filter { task ->
                val (givenCount, users) = givenCountMap[task.id] ?: (0 to mutableSetOf())
                givenCount < task.overlapCoefficient && user !in users
            }
            .next()
            .flatMap { task ->
                incrementGivenCount(task, user).thenReturn(task)
            }
    }

    fun saveTask(task: Task): Mono<Long> {
        return redisTemplate.opsForList().rightPush(task.taskTypeName, task)
    }

    private fun incrementGivenCount(task: Task, user: String): Mono<Boolean> {
        return Mono.fromCallable {
            givenCountMap.compute(task.id) { _, current ->
                val (givenCount, users) = current ?: (0 to mutableSetOf())
                users.add(user)
                (givenCount + 1) to users
            }
            true
        }
    }
}





