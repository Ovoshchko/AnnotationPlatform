package ru.itmo.ai.school.ecom.api.apiservice.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.client.BatchClient
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.BatchUploadRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.toTaskServiceBatchUploadRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchDtoResponse
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchFromTaskServiceDtoListResponse
import java.util.*

@Service
class BatchService(
    private val batchClient: BatchClient
) {

    fun getBatches(owner: String?, page: Int, size: Int): Mono<BatchFromTaskServiceDtoListResponse> {
        return batchClient.getAllBatches(owner, page, size)
    }

    fun uploadBatch(batchUploadRequest: BatchUploadRequest, owner: String): Mono<Void> {
        return batchClient.uploadBatch(batchUploadRequest.toTaskServiceBatchUploadRequest(owner))
    }

    fun getBatchById(id: UUID): Mono<BatchDtoResponse> {
        return batchClient.getBatchById(id)
    }

}