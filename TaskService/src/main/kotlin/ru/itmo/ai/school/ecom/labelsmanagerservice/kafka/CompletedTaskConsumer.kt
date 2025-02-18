package ru.itmo.ai.school.ecom.labelsmanagerservice.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.CompletedTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.service.TaskService

@Service
class CompletedTaskConsumer(
    private val taskService: TaskService,
) {
    private val log = LoggerFactory.getLogger(CompletedTaskConsumer::class.java)

    @KafkaListener(topics = ["completed-tasks"])
    fun consume(completedTask: CompletedTaskDto) {
        log.info("Received completed task: {}", completedTask)
        taskService.completeTask(completedTask)
        log.info("Successfully completed task {}", completedTask)
    }
}
