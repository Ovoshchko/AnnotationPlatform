package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TaskServiceBatchUploadRequest (
    val batchName: String,
    val owner: String,
    val overlapCoefficient: Int = 1,
    val priority: Int = 3,
    val taskType: String,
    val taskTypeName: String = "",
    val agreements: Map<String, Any> = emptyMap(),
    val isEducational: Boolean = false,
    val tasks: List<Map<String, Any?>> = emptyList()
)