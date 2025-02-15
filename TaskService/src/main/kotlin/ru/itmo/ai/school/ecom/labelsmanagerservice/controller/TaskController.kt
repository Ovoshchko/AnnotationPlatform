package ru.itmo.ai.school.ecom.labelsmanagerservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.TaskDtoResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.service.TaskService
import java.util.UUID

@RestController
@RequestMapping("/task")
class TaskController(
    val taskService: TaskService
) {

    @GetMapping("/{taskId}")
    fun getTaskById(
        @PathVariable taskId: UUID
    ): TaskDtoResponse {
        return taskService.getTaskById(taskId)
    }
}