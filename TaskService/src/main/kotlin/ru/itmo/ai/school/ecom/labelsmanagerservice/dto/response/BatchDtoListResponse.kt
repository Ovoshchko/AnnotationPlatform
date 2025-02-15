package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchDtoListResponse(
    val batches: List<BatchDtoResponse>,
    val pageNumber: Int
)
