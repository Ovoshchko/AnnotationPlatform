package ru.itmo.ai.school.ecom.api.apiservice.service

import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.BatchUploadRequest
import java.util.*

@Service
class BatchService(
    private val taskTypeService: TaskTypeService
) {

    fun uploadBatch(batchUploadRequest: BatchUploadRequest) {
        val taskType = taskTypeService.getTaskTypeById(UUID.fromString(batchUploadRequest.taskType))
    }

}