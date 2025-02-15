package ru.itmo.ai.school.ecom.labelsmanagerservice.db.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.TaskDtoResponse
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "task")
data class Task(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, length = 255)
    val name: String = "",

    @Column(name = "s3_path", nullable = false)
    val s3Path: String = "",

    @ManyToOne
    @JoinColumn(name = "batch_id", nullable = false)
    val batch: Batch = Batch(),

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    val metadata: Map<String, Any?>? = null,

    @Column(name = "is_honeypot", nullable = false)
    val isHoneypot: Boolean = false,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: TaskStatus = TaskStatus.NOT_STARTED,

    @Column(name = "final_answer", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    val finalAnswer: Map<String, Any?>? = null,

    @Column(name = "properties", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    val properties: Map<String, Any?> = emptyMap(),

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)

fun Task.toDto(): TaskDtoResponse {
    return TaskDtoResponse(
        taskId = this.id,
        name = this.name,
        s3Path = this.s3Path,
        batchId = this.batch.id,
        metadata = this.metadata,
        isHoneypot = this.isHoneypot,
        status = this.status,
        finalAnswer = this.finalAnswer,
        properties = this.properties,
        createdAt = this.createdAt
    )
}
