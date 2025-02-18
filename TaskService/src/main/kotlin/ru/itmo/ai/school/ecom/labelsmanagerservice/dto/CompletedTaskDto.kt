package ru.itmo.ai.school.ecom.labelsmanagerservice.dto

import java.util.*

data class CompletedTaskDto(
    val taskId: UUID,
    val finalAnswer: Map<String, Any?>,
)
