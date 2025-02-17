package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.BatchUploadRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchDtoListResponse
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchDtoResponse
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.toBatchListInfoResponse
import ru.itmo.ai.school.ecom.api.apiservice.service.BatchService
import java.util.UUID

@RestController
@RequestMapping("/v1/batch")
class BatchController(
    private val batchService: BatchService
) {

    @PostMapping
    fun uploadBatch(exchange: ServerWebExchange, @RequestBody batchUploadRequest: BatchUploadRequest) {
        batchService.uploadBatch(batchUploadRequest,exchange.getAttribute("owner") ?: "basic")
            .subscribe()
    }

    @GetMapping
    fun getAllBatches(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false) owner: String?
    ): Mono<BatchDtoListResponse> {
        return batchService.getBatches(owner, page, size).map { it.toBatchListInfoResponse(size) }
    }

    @GetMapping("/{id}")
    fun getBatch(
        @PathVariable id: UUID
    ): Mono<BatchDtoResponse> {
        return batchService.getBatchById(id).map { it }
    }
}