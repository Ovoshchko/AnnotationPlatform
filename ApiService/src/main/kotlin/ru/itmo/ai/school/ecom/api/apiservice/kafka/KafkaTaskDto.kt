package ru.itmo.ai.school.ecom.api.apiservice.kafka

import ru.itmo.ai.school.ecom.api.apiservice.model.Task
import java.util.UUID
import java.time.LocalDateTime

data class KafkaTaskDto(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val batchId: UUID = UUID.randomUUID(),
    val taskTypeName: String = "unknown",
    val metadata: Map<String, Any?>? = null,
    val isHoneypot: Boolean = false,
    val status: String = "NOT_STARTED",
    val finalAnswer: Map<String, Any?>? = null,
    val properties: Map<String, Any?> = emptyMap(),
    val createdAt: LocalDateTime = LocalDateTime.now()
)

fun KafkaTaskDto.toTask(): Task {
    return Task(
        id = this.id,
        name = this.name,
        batchId = this.batchId,
        taskTypeName = this.taskTypeName,
        metadata = this.metadata,
        isHoneypot = this.isHoneypot,
        status = this.status,
        finalAnswer = this.finalAnswer,
        properties = this.properties,
        overlapCoefficient = metadata?.get("overlapCoefficient")?.toString()?.toInt() ?: 1
    )
}
