package ru.itmo.ai.school.ecom.api.apiservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchDtoListResponse(
    val batches: List<BatchDtoResponse>,
    val pageNumber: Int
)

fun BatchDtoListResponse.toBatchListInfoResponse(pageSize: Int): BatchListInfoResponse {
    return BatchListInfoResponse(
        batches = this.batches.map { batch ->
            BatchInfo(
                batchName = batch.batchName,
                owner = batch.owner,
                taskType = batch.taskType,
                isEducational = batch.isEducational
            )
        },
        pageNumber = this.pageNumber,
        pageSize = pageSize
    )
}