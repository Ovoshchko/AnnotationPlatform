package ru.itmo.ai.school.ecom.api.apiservice.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.api.apiservice.dto.answer.FilledTaskDto

@Service
class AnswerService(
    private val kafkaTemplate: KafkaTemplate<String, FilledTaskDto>,
) {
    private val log = LoggerFactory.getLogger(AnswerService::class.java)
    private val FILLED_TASK_TOPIC = "answer"

    fun sendFilledTask(filledTaskDto: FilledTaskDto) {
        kafkaTemplate.executeInTransaction { kafkaOps ->
            kafkaOps.send(FILLED_TASK_TOPIC, filledTaskDto.filledBy, filledTaskDto)
        }
        log.info("Sent task filled by ${filledTaskDto.filledBy} to kafka from api-service")
    }
}
