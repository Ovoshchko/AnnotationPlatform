package ru.itmo.ai.school.ecom.labelsmanagerservice.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.Task
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.TaskStatus
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.toDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.repository.TaskRepository
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.CompletedTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.TaskDtoResponse
import java.util.*

@Service
class TaskService(
    private val taskRepository: TaskRepository,
) {

    fun getTaskById(taskId: UUID): TaskDtoResponse {
        return findTaskById(taskId).toDto()
    }

    fun getTasksByBatch(batchId: UUID, page: Int, size: Int): Page<Task> {
        val pageable = PageRequest.of(page, size, Sort.by("createdAt").descending())
        return taskRepository.findTasksByBatchId(batchId, pageable)
    }

    fun findTaskById(taskId: UUID): Task {
        return taskRepository.findById(taskId)
            .orElseThrow { EntityNotFoundException("Task with id $taskId not found") }
    }

    fun saveAllTasks(tasks: List<Task>): List<Task> {
        return taskRepository.saveAll(tasks)
    }

    fun saveTask(task: Task): Task {
        return taskRepository.save(task)
    }

    @Transactional
    fun completeTask(completedTaskDto: CompletedTaskDto) {
        val task = taskRepository.findById(completedTaskDto.taskId)
            .orElseThrow { EntityNotFoundException("Task with id ${completedTaskDto.taskId} not found") }
        if (task.status == TaskStatus.COMPLETED) {
            return
        }
        task.finalAnswer = completedTaskDto.finalAnswer
        task.status = TaskStatus.COMPLETED
        taskRepository.save(task)
    }
}