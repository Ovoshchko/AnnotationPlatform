package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.BatchUploadRequest

@RestController
@RequestMapping("/v1/batch")
class BatchController {

    @PostMapping("/upload")
    fun uploadBatch(@RequestBody batchUploadRequest: BatchUploadRequest) {

    }
}