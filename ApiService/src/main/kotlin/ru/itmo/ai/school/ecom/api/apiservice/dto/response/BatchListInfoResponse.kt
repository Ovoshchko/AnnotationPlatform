package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchListInfoResponse(
    val batches: List<BatchInfo>,
    val pageNumber: Int,
    val pageSize: Int
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchInfo(
    val batchName: String,
    val owner: String,
    val taskType: UUID,
    val isEducational: Boolean
)