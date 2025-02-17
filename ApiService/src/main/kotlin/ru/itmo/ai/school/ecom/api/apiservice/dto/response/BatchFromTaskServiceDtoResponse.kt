package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchFromTaskServiceDtoResponse(
    val id: UUID,
    val name: String,
    val owner: String,
    val priority: Int,
    val agreements: Map<String, Any>?,
    val overlapCoefficient: Int,
    val createdAt: LocalDateTime,
    val isEducational: Boolean,
    val taskType: UUID
)
