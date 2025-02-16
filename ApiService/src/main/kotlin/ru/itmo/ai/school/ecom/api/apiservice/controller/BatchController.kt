package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.BatchUploadRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.BatchListInfoResponse
import ru.itmo.ai.school.ecom.api.apiservice.service.BatchService

@RestController
@RequestMapping("/v1/batch")
class BatchController(
    private val batchService: BatchService
) {

    @PostMapping("/upload")
    fun uploadBatch(@RequestBody batchUploadRequest: BatchUploadRequest) {
        batchService.uploadBatch(batchUploadRequest)
            .subscribe()
    }

    @GetMapping("/all")
    fun getAllBatches(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false) owner: String?
    ): Mono<BatchListInfoResponse> {
        return batchService.getBatches(owner, page, size)
    }
}