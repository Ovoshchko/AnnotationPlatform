package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskTypeDto
import ru.itmo.ai.school.ecom.api.apiservice.service.TaskTypeService

@RestController
@RequestMapping("/v1/task-type")
class TaskTypeController(
    private val taskTypeService: TaskTypeService
) {

    @GetMapping("/all")
    fun getAllTypes(): Mono<List<TaskTypeDto>> {
        return taskTypeService.getAllTasks()
    }
}