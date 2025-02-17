package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.AssessorStats
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.BatchStats
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.GlobalStats
import java.util.*

@RequestMapping("/stats")
@RestController
class StatsController {
    @GetMapping("/assessor/{login}")
    fun getStatsByAssessor(@PathVariable login: String): AssessorStats {
        return AssessorStats(10, 20)
    }

    @GetMapping("/batch/{batchId}")
    fun getStatsByBatch(@PathVariable batchId: UUID): BatchStats {
        return BatchStats(10)
    }

    @GetMapping
    fun getGlobalStats(): GlobalStats {
        return GlobalStats(
            30, mapOf(
                "Разметка поиска" to 10,
                "Разметка поиска" to 20
            )
        )
    }
}