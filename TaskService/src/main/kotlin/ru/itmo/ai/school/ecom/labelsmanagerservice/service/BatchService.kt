package ru.itmo.ai.school.ecom.labelsmanagerservice.service

import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.Batch
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.Task
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.toDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.toDtoWithTasks
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.repository.BatchRepository
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.request.BatchUploadRequest
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.BatchDtoListResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.BatchWithTasksResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.kafka.TaskProducer
import java.time.LocalDateTime
import java.util.*

@Service
class BatchService(
    private val batchRepository: BatchRepository,
    private val taskService: TaskService,
    private val taskProducer: TaskProducer
) {

    @Transactional
    fun uploadBatch(batchUploadRequest: BatchUploadRequest) {
        val batch = saveBatch(
            Batch(
                name = batchUploadRequest.batchName,
                owner = batchUploadRequest.owner,
                priority = batchUploadRequest.priority,
                agreements = batchUploadRequest.agreements,
                overlapCoefficient = batchUploadRequest.overlapCoefficient,
                createdAt = LocalDateTime.now(),
                taskType = UUID.fromString(batchUploadRequest.taskType)
            )
        )

        batchUploadRequest.tasks.chunked(50).forEach { taskChunk ->
            val tasksToSave = taskChunk.mapIndexed { index, task ->
                Task(
                    name = "${batch.name}_task_${index}",
                    s3Path = "",
                    batch = Batch(id = batch.id),
                    metadata = task
                )
            }

            taskService.saveAllTasks(tasksToSave)

            taskProducer.sendTasks(tasksToSave, batchUploadRequest.taskTypeName)
        }
    }

    fun getBatchWithTasks(batchId: UUID, page: Int, size: Int): BatchWithTasksResponse {
        val batch = batchRepository.findById(batchId)
            .orElseThrow { EntityNotFoundException("Batch with ID $batchId not found") }

        val tasksPage = taskService.getTasksByBatch(batchId, page, size)

        return batch.toDtoWithTasks(tasksPage.toList().map { it.toDto() }, page)
    }

    fun getBatches(owner: String?, page: Int, size: Int): BatchDtoListResponse {
        val pageable = PageRequest.of(page, size)

        val batches = if (owner != null) {
            batchRepository.findByOwner(owner, pageable)
        } else {
            batchRepository.findAll(pageable)
        }.map { it.toDto() }

        return BatchDtoListResponse(batches.content, page)
    }

    fun saveBatch(batch: Batch): Batch {
        return batchRepository.save(batch)
    }
}