package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.CompletedTaskDto

@Service
class CompletedTaskProducer(
    val kafkaTemplate: KafkaTemplate<String, CompletedTaskDto>,
) {
    private val log = LoggerFactory.getLogger(CompletedTaskProducer::class.java)
    fun publishCompletedTask(completedTaskDto: CompletedTaskDto) {
        kafkaTemplate.send("completed-tasks", completedTaskDto.taskId.toString(), completedTaskDto)
        log.info("Sent completed task to kafka 'completed-tasks'")
    }
}