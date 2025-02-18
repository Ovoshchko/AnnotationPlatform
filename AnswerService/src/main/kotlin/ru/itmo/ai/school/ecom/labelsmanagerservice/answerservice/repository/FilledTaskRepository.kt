package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.entity.FilledTask
import java.util.*

interface FilledTaskRepository : JpaRepository<FilledTask, UUID> {
    @Query("SELECT COUNT(*) FROM FilledTask ft WHERE ft.filledBy = :assessorLogin")
    fun countByAssessor(@Param("assessorLogin") assessorLogin: String): Long

    @Query(
        "SELECT COUNT(*) FROM task_filled WHERE task_id = :taskId AND answer = CAST(:jsonAnswer AS jsonb)",
        nativeQuery = true
    )
    fun countByTaskIdAndAnswer(@Param("taskId") taskId: UUID, @Param("jsonAnswer") jsonAnswer: String): Long
}