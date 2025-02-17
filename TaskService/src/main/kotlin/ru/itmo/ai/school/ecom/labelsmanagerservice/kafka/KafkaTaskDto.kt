package ru.itmo.ai.school.ecom.labelsmanagerservice.kafka

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
