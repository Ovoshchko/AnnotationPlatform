package ru.itmo.ai.school.ecom.api.apiservice.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import ru.itmo.ai.school.ecom.api.apiservice.db.model.TaskType
import ru.itmo.ai.school.ecom.api.apiservice.db.model.toDto
import ru.itmo.ai.school.ecom.api.apiservice.db.repository.TaskTypeRepository
import ru.itmo.ai.school.ecom.api.apiservice.dto.request.TaskTypeCreateRequest
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskTypeDto
import java.util.UUID

@Service
class TaskTypeService(
    private val taskTypeRepository: TaskTypeRepository
) {

    fun getAllTasks(): Mono<List<TaskTypeDto>> {
        return Mono.fromCallable { taskTypeRepository.findAll() }
            .map {
                it.map {taskType ->
                    taskType.toDto()
                }
            }
    }

    fun saveTaskType(taskTypeCreateRequest: TaskTypeCreateRequest): Mono<TaskType> {
        return Mono.fromCallable {
            taskTypeRepository.save(
                TaskType(
                    name = taskTypeCreateRequest.name,
                    description = taskTypeCreateRequest.description,
                    annotationMetadata = taskTypeCreateRequest.annotationMetadata
                )
            )
        }
    }

    fun getTaskTypeById(id: UUID): Mono<TaskType> {
        return Mono.fromCallable {
            taskTypeRepository.findById(id)
                .orElseThrow { EntityNotFoundException("Task with id $id not found") }
        }
    }
}