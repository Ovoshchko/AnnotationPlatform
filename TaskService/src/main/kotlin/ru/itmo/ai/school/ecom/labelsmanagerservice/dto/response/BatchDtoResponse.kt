package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.apache.kafka.common.protocol.types.Field.Bool
import java.time.LocalDateTime
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchDtoResponse(
    val batchId: UUID,
    val batchName: String,
    val owner: String,
    val priority: Int,
    val agreements: Map<String, Any>?,
    val overlapCoefficient: Int,
    val createdAt: LocalDateTime,
    val isEducational: Boolean,
    val taskType: UUID
)
