package ru.itmo.ai.school.ecom.api.apiservice.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.client.BatchClient
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.BatchUploadRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.toTaskServiceBatchUploadRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchListInfoResponse
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.toBatchListInfoResponse

@Service
class BatchService(
    private val batchClient: BatchClient
) {

    fun getBatches(owner: String?, page: Int, size: Int): Mono<BatchListInfoResponse> {
        return batchClient.getAllBatches(owner, page, size)
            .map { it.toBatchListInfoResponse(size) }
    }

    fun uploadBatch(batchUploadRequest: BatchUploadRequest): Mono<Void> {
        return batchClient.uploadBatch(batchUploadRequest.toTaskServiceBatchUploadRequest())
    }

}