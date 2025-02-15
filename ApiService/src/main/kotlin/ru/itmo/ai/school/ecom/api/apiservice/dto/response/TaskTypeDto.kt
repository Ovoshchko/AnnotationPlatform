package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TaskTypeDto(
    val id: UUID,
    val name: String,
    val annotationMetadata: Map<String, Any>?,
    val description: String?
)