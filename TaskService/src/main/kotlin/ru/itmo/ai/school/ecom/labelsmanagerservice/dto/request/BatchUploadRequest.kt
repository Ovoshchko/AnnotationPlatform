package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchUploadRequest(
    val name: String,
    val owner: String,
    val overlapCoefficient: Int = 1,
    val priority: Int = 3,
    val taskType: String,
    val taskTypeName: String = "",
    val agreements: Map<String, Any> = emptyMap(),
    val isEducational: Boolean = false,
    val tasks: List<Map<String, Any?>> = emptyList()
)
