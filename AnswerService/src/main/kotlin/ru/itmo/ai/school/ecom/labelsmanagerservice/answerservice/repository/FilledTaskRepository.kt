package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.entity.FilledTask
import java.util.*

interface FilledTaskRepository : JpaRepository<FilledTask, UUID>


fun countByAssessor() : Long {TODO()}