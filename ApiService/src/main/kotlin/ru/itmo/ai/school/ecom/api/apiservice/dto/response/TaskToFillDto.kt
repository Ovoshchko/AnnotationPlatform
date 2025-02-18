package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TaskToFillDto(
    val id: UUID,
    val metadata: Map<String, Any?>,
    val isHoneypot: Boolean,
    val parameters: Map<String, Any?>
)
