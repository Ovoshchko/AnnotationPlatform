package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.FilledTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.toEntity
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.repository.FilledTaskRepository
import java.util.*

@Service
class FilledTaskService(
    val filledTaskRepository: FilledTaskRepository,
    val objectMapper: ObjectMapper,
) {
    private val log = LoggerFactory.getLogger(FilledTaskService::class.java)

    fun saveTask(filledTaskDto: FilledTaskDto, isCorrect: Boolean?): UUID {
        val saved = filledTaskRepository.save(filledTaskDto.toEntity(isCorrect))
        log.info("Saved filled task: {} for task {}", saved.id, filledTaskDto.taskId)
        return saved.id
    }

    fun countByTaskIdAndAnswer(filledTaskDto: FilledTaskDto): Long {
        val jsonAnswer = objectMapper.writeValueAsString(filledTaskDto.answer);
        return filledTaskRepository.countByTaskIdAndAnswer(filledTaskDto.taskId, jsonAnswer)
    }
}
