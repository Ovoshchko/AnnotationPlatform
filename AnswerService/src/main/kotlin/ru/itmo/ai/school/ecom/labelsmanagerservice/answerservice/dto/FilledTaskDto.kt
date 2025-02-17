package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto

import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.entity.FilledTask
import java.util.*

data class FilledTaskDto(
    val taskId: UUID,
    val filledBy: String,
    val answer: Map<String, String>,
    val metadata: Map<String, Any?>,
    val isHoneypot: Boolean,
)

fun FilledTaskDto.toEntity(): FilledTask {
    return FilledTask(
        taskId = taskId,
        filledBy = filledBy
    )
}