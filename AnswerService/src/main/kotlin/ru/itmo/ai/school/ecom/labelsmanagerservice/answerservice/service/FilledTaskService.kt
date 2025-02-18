package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.service

import org.springframework.stereotype.Service
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.FilledTaskDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.toEntity
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.repository.FilledTaskRepository
import java.util.*

@Service
class FilledTaskService(val filledTaskRepository: FilledTaskRepository) {
    fun saveTask(filledTaskDto: FilledTaskDto): UUID {
        val saved = filledTaskRepository.save(filledTaskDto.toEntity())
        return saved.id
    }
}
