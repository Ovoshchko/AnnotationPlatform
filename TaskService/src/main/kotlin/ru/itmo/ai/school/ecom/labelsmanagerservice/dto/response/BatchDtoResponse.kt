package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response

import java.time.LocalDateTime
import java.util.*

data class BatchDtoResponse(
    val batchId: UUID,
    val batchName: String,
    val owner: String,
    val priority: Int,
    val agreements: Map<String, Any>?,
    val overlapCoefficient: Int,
    val createdAt: LocalDateTime,
    val taskType: UUID
)
