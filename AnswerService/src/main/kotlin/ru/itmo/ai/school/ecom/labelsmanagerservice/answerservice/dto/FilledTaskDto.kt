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

fun FilledTaskDto.toEntity(isCorrect: Boolean?): FilledTask {
    return FilledTask(
        taskId = taskId,
        filledBy = filledBy,
        isCorrect = isCorrect,
    )
}

fun FilledTaskDto.getOverlap(): Int {
    return (metadata["overlap"] ?: throw RuntimeException("Could not get overlap")) as Int
}

fun FilledTaskDto.getCorrectAnswer(): Map<String, Any?> {
    if (isHoneypot) {
        return (metadata["correctAnswer"]
            ?: throw RuntimeException("Could not get correctAnswer from honeypot task")) as Map<String, Any?>
    }
    throw RuntimeException("Could not get correctAnswer because task is not honeypot")
}