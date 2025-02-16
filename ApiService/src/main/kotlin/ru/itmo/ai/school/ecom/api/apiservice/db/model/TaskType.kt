package ru.itmo.ai.school.ecom.api.apiservice.db.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskTypeDto
import java.util.UUID

@Entity
@Table(name = "task_type")
data class TaskType(
    @Id
    val id: UUID? = UUID.randomUUID(),

    @Column(nullable = false, length = 255)
    val name: String,

    @Column(name = "annotation_metadata", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    val annotationMetadata: Map<String, Any>? = null,

    @Column(columnDefinition = "text")
    val description: String? = null
)

fun TaskType.toDto(): TaskTypeDto {
    return TaskTypeDto(
        id = this.id ?: UUID.randomUUID(),
        name = this.name,
        annotationMetadata = this.annotationMetadata,
        description = this.description
    )
}
