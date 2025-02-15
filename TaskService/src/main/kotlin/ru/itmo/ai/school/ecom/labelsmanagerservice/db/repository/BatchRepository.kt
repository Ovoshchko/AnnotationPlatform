package ru.itmo.ai.school.ecom.labelsmanagerservice.db.repository

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import ru.itmo.ai.school.ecom.labelsmanagerservice.db.model.Batch
import java.util.*

interface BatchRepository : JpaRepository<Batch, UUID> {

    @Transactional
    fun save(batch: Batch): Batch

    fun findByOwner(owner: String, pageable: Pageable): Page<Batch>
}