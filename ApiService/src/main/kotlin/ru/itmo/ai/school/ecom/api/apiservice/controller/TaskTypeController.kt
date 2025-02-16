package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.db.model.toDto
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.TaskTypeCreateRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskTypeDto
import ru.itmo.ai.school.ecom.api.apiservice.service.TaskTypeService
import java.util.UUID

@RestController
@RequestMapping("/v1/task-type")
class TaskTypeController(
    private val taskTypeService: TaskTypeService
) {

    @GetMapping
    fun getAllTypes(): Mono<List<TaskTypeDto>> {
        return taskTypeService.getAllTasks()
    }

    @PostMapping
    fun addTaskType(@RequestBody taskTypeCreateRequest: TaskTypeCreateRequest): Mono<TaskTypeDto> {
        return taskTypeService.saveTaskType(taskTypeCreateRequest).map { it.toDto() }
    }

    @GetMapping("/{id}")
    fun getTaskType(
        @PathVariable id: UUID
    ): Mono<TaskTypeDto> {
        return taskTypeService.getTaskTypeById(id).map { it.toDto() }
    }
}