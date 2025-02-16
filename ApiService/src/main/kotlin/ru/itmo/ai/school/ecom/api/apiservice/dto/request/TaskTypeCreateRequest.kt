package ru.itmo.ai.school.ecom.api.apiservice.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TaskTypeCreateRequest(
    val name: String,
    val annotationMetadata: Map<String, Any>?,
    val description: String?
)
