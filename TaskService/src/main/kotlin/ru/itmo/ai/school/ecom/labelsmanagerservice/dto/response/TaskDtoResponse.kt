package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.TaskStatus
import java.time.LocalDateTime
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TaskDtoResponse(
    val taskId: UUID,
    val name: String,
    val s3Path: String,
    val batchId: UUID,
    val metadata: Map<String, Any?>?,
    val isHoneypot: Boolean,
    val status: TaskStatus,
    val finalAnswer: Map<String, Any?>?,
    val properties: Map<String, Any?>,
    val createdAt: LocalDateTime
)