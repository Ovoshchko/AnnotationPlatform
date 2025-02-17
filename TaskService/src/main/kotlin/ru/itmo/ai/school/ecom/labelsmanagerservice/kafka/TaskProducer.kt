package ru.itmo.ai.school.ecom.labelsmanagerservice.kafka

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class TaskProducer(
    private val kafkaTemplate: KafkaTemplate<String, KafkaTaskDto>
) {
    private val log = LoggerFactory.getLogger(TaskProducer::class.java)
    private val topic = "task"

    fun sendTasks(tasks: List<KafkaTaskDto>, taskTypeName: String) {
        if (tasks.isEmpty()) return

        kafkaTemplate.executeInTransaction { kafkaOperations ->
            tasks.forEach { task ->
                kafkaOperations.send(topic, task.id.toString(), task)
            }
        }
    }

    fun sendTask(task: KafkaTaskDto, taskTypeName: String) {
        kafkaTemplate.send(topic, task.id.toString(), task)
    }
}
