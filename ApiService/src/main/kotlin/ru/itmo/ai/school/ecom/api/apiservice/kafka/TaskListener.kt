package ru.itmo.ai.school.ecom.api.apiservice.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.api.apiservice.service.TaskRedisService

@Service
class TaskListener(
    private val taskRedisService: TaskRedisService
) {

    @KafkaListener(topics = ["task"], groupId = "task-group")
    fun listen(task: KafkaTaskDto) {
        taskRedisService.saveTask(task.toTask()).subscribe()
    }
}
