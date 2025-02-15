package ru.itmo.ai.school.ecom.labelsmanagerservice.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class BatchUploadRequest(
    @JsonProperty("batch_name")
    val batchName: String,

    @JsonProperty("owner")
    val owner: String,

    @JsonProperty("overlap_coefficient")
    val overlapCoefficient: Int = 1,

    @JsonProperty("priority")
    val priority: Int = 3,

    @JsonProperty("task_type")
    val taskType: String,

    @JsonProperty("task_type_name")
    val taskTypeName: String = "",

    @JsonProperty("agreements")
    val agreements: Map<String, Any> = emptyMap(),

    @JsonProperty("is_educational")
    val isEducational: Boolean = false,

    @JsonProperty("tasks")
    val tasks: List<Map<String, Any?>> = emptyList()
)
