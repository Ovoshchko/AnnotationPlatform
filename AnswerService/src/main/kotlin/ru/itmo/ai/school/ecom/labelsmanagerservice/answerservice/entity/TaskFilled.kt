package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "task_filled")
data class FilledTask(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val taskId: UUID,

    @Column(columnDefinition = "varchar(255)", nullable = false)
    val filledBy: String,

    @Column(columnDefinition = "jsonb", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    val answer: Map<String, Any?>? = null,

    @Column(name = "created_at", nullable = false)
    val filledAt: LocalDateTime = LocalDateTime.now(),
)
