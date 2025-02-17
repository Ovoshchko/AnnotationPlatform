package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchDtoResponse(
    val batchName: String,
    val owner: String,
    val taskType: UUID,
    val isEducational: Boolean,
    val createdAt: LocalDateTime
)