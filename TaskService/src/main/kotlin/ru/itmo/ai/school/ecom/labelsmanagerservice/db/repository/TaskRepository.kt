package ru.itmo.ai.school.ecom.labelsmanagerservice.db.repository

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.Task
import java.util.*

interface TaskRepository : JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t WHERE t.batch.id = :batchId")
    fun findTasksByBatchId(batchId: UUID, pageable: Pageable): Page<Task>

    @Transactional
    fun save(task: Task): Task
}