package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.FilledTaskDto

@Service
class FilledTaskConsumer(val filledTaskService: FilledTaskService,
                         val statsService: StatsService) {

    @KafkaListener(topics = ["filled-tasks"], groupId = "filled-task-group")
    fun consume(filledTask: FilledTaskDto) {
        println("Received Filled Task: $filledTask")
        val filledTaskId = filledTaskService.saveTask(filledTask)
        statsService.processTask(filledTask)
    }
}
