package ru.itmo.ai.school.ecom.api.apiservice.dto.answer

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class FilledTaskRequestDto(
    val taskId: UUID,
    val answer: Map<String, Any?>,
    val metadata: Map<String, Any?>,
    val isHoneypot: Boolean,
)

