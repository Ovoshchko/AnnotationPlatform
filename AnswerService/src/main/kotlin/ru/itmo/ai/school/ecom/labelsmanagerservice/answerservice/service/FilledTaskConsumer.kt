package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.CompletedTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.FilledTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.getCorrectAnswer
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.getOverlap

@Service
class FilledTaskConsumer(
    val filledTaskService: FilledTaskService,
    val statsService: StatsService,
    val completedTaskProducer: CompletedTaskProducer,
) {
    private val log = LoggerFactory.getLogger(FilledTaskConsumer::class.java)

    @KafkaListener(topics = ["answer"])
    fun consume(filledTask: FilledTaskDto) {
        log.info("Received filled task: {}", filledTask)
        var isCorrect: Boolean? = null
        if (filledTask.isHoneypot) {
            isCorrect = filledTask.getCorrectAnswer() == filledTask.answer
        } else {
            val sameAnswerTaskCount = filledTaskService.countByTaskIdAndAnswer(filledTask)
            if (sameAnswerTaskCount + 1 >= filledTask.getOverlap()) {
                completedTaskProducer.publishCompletedTask(
                    CompletedTaskDto(filledTask.taskId, filledTask.answer)
                )
                isCorrect = true
            }
        }
        val filledTaskId = filledTaskService.saveTask(filledTask, isCorrect)
        statsService.processTask(filledTask, isCorrect)
    }
}

