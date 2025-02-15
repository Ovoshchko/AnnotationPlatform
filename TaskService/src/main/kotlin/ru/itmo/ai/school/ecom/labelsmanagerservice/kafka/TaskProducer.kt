package ru.itmo.ai.school.ecom.labelsmanagerservice.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.Task

@Service
class TaskProducer(
    private val kafkaTemplate: KafkaTemplate<String, Task>
) {
    private val log = LoggerFactory.getLogger(TaskProducer::class.java)
    private val topic = "tasks"

    fun sendTasks(tasks: List<Task>, taskTypeName: String) {
        if (tasks.isEmpty()) return

        kafkaTemplate.executeInTransaction { kafkaOperations ->
            tasks.forEach { task ->
                kafkaOperations.send(getTopicName(taskTypeName), task.id.toString(), task)
            }
        }
    }

    fun sendTask(task: Task, taskTypeName: String) {
        kafkaTemplate.send(getTopicName(taskTypeName), task.id.toString(), task)
    }

    private fun getTopicName(taskTypeName: String): String {
        return topic + "_" + taskTypeName
    }
}
