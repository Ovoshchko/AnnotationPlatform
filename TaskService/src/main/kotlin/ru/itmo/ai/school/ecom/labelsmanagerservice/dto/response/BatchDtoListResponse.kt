package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response

data class BatchDtoListResponse(
    val batches: List<BatchDtoResponse>,
    val pageNumber: Int
)
