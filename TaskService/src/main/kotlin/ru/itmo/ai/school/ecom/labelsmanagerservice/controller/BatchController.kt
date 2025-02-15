package ru.itmo.ai.school.ecom.labelsmanagerservice.controller

import org.springframework.web.bind.annotation.*
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.request.BatchUploadRequest
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.BatchDtoListResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.BatchWithTasksResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.service.BatchService
import java.util.*

@RestController
@RequestMapping("/batch")
class BatchController(
    private val batchService: BatchService
) {

    @GetMapping("/all")
    fun getAllBatches(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false) owner: String?
    ): BatchDtoListResponse {
        return batchService.getBatches(owner, page, size)
    }

    @GetMapping("/{batchId}")
    fun getBatchWithTasks(
        @PathVariable batchId: UUID,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "50") size: Int
    ): BatchWithTasksResponse {
        return batchService.getBatchWithTasks(batchId, page, size)
    }

    @PostMapping("/upload")
    fun uploadBatch(@RequestBody batchUploadRequest: BatchUploadRequest) {
        batchService.uploadBatch(batchUploadRequest)
    }
}