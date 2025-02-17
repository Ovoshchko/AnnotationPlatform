package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchFromTaskServiceDtoListResponse(
    val batches: List<BatchFromTaskServiceDtoResponse>,
    val pageNumber: Int
)

fun BatchFromTaskServiceDtoListResponse.toBatchListInfoResponse(pageSize: Int): BatchDtoListResponse {
    return BatchDtoListResponse(
        batches = this.batches.map { batch ->
            BatchDtoResponse(
                batchName = batch.batchName,
                owner = batch.owner,
                taskType = batch.taskType,
                isEducational = batch.isEducational,
                createdAt = batch.createdAt
            )
        },
        pageNumber = this.pageNumber,
        pageSize = pageSize
    )
}