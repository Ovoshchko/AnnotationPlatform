package ru.itmo.ai.school.ecom.api.apiservice.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskToFillDto
import java.util.UUID

data class Task @JsonCreator constructor(
    @JsonProperty("id") val id: UUID = UUID.randomUUID(),
    @JsonProperty("name") val name: String = "",
    @JsonProperty("batchId") val batchId: UUID = UUID.randomUUID(),
    @JsonProperty("taskTypeName") val taskTypeName: String = "unknown",
    @JsonProperty("metadata") val metadata: Map<String, Any?>? = null,
    @JsonProperty("honeypot") val isHoneypot: Boolean = false,
    @JsonProperty("status") val status: String = "NOT_STARTED",
    @JsonProperty("finalAnswer") val finalAnswer: Map<String, Any?>? = null,
    @JsonProperty("properties") val properties: Map<String, Any?> = emptyMap(),
    @JsonProperty("overlapCoefficient") val overlapCoefficient: Int = 1,
    @JsonProperty("givenCount") val givenCount: Int = -1,
    @JsonProperty("filledCount") val filledCount: Int = 0
)

fun Task.toFillDto(): TaskToFillDto {
    return TaskToFillDto(
        id = this.id,
        metadata = mapOf("overlap_coef" to this.overlapCoefficient),
        isHoneypot = this.isHoneypot,
        parameters = this.properties
    )
}

