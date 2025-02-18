package ru.itmo.ai.school.ecom.api.apiservice.dto.answer

import java.util.*

data class FilledTaskDto(
    val taskId: UUID,
    var filledBy: String,
    val answer: Map<String, String>,
    val metadata: Map<String, Any?>,
    val isHoneypot: Boolean,
)

