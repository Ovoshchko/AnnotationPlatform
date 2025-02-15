package ru.itmo.ai.school.ecom.labelsmanagerservice.db.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.BatchDtoResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.BatchWithTasksResponse
import ru.itmo.ai.school.ecom.labelsmanagerservice.dto.response.TaskDtoResponse
import java.util.UUID
import java.time.LocalDateTime

@Entity
@Table(name = "batch")
data class Batch(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false, length = 255)
    val name: String = "",

    @Column(nullable = false, length = 255)
    val owner: String = "",

    @Column(nullable = false)
    val priority: Int = 1,

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    val agreements: Map<String, Any>? = null,

    @Column(name = "overlap_coefficient", nullable = false)
    val overlapCoefficient: Int = 1,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "task_type_id", nullable = false)
    val taskType: UUID = UUID.randomUUID(),

    @Column(name = "is_educational", nullable = false)
    val isEducational: Boolean = false
)

fun Batch.toDtoWithTasks(tasks: List<TaskDtoResponse>, tasksPage: Int): BatchWithTasksResponse {
    return BatchWithTasksResponse(
        batchId = this.id,
        batchName = this.name,
        owner = this.owner,
        priority = this.priority,
        agreements = this.agreements,
        overlapCoefficient = this.overlapCoefficient,
        createdAt = this.createdAt,
        taskType = this.taskType,
        tasks = tasks,
        tasksPage = tasksPage
    )
}

fun Batch.toDto(): BatchDtoResponse {
    return BatchDtoResponse(
        batchId = this.id,
        batchName = this.name,
        owner = this.owner,
        priority = this.priority,
        agreements = this.agreements,
        overlapCoefficient = this.overlapCoefficient,
        createdAt = this.createdAt,
        taskType = this.taskType
    )
}

