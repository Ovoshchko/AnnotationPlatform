package ru.itmo.ai.school.ecom.api.apiservice.dto.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskServiceBatchUploadRequest
import kotlin.random.Random

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BatchUploadRequest(
    val batchName: String,
    val overlapCoefficient: Int = 1,
    val priority: Int = 3,
    val taskType: String,
    val taskTypeName: String = "",
    val agreements: Map<String, Any> = emptyMap(),
    val isEducational: Boolean = false,
    val tasks: List<Map<String, Any?>> = emptyList()
)

fun BatchUploadRequest.toTaskServiceBatchUploadRequest(): TaskServiceBatchUploadRequest {
    return TaskServiceBatchUploadRequest(
        batchName = this.batchName,
        owner = "owner" + Random.nextInt(10),
        overlapCoefficient = this.overlapCoefficient,
        priority = this.priority,
        taskType = this.taskType,
        taskTypeName = this.taskTypeName,
        agreements = this.agreements,
        isEducational = this.isEducational,
        tasks = this.tasks
    )
}