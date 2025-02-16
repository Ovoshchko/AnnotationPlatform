package ru.itmo.ai.school.ecom.api.apiservice.client

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchDtoListResponse
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskServiceBatchUploadRequest

@Component
class BatchClient(
    @Qualifier("batchWebClient") private val webClient: WebClient
) {

    fun uploadBatch(batchUploadRequest: TaskServiceBatchUploadRequest): Mono<Void> {
        return webClient.post()
            .uri("/batch/upload")
            .bodyValue(batchUploadRequest)
            .retrieve()
            .bodyToMono(Void::class.java)
            .then()
    }

    fun getAllBatches(owner: String?, page: Int, size: Int): Mono<BatchDtoListResponse> {
        return webClient.get()
            .uri { builder ->
                builder.path("/batch/all")
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .apply {
                        if (owner != null) {
                            queryParam("owner", owner)
                        }
                    }
                    .build()
            }
            .retrieve()
            .bodyToMono(BatchDtoListResponse::class.java)
    }
}
