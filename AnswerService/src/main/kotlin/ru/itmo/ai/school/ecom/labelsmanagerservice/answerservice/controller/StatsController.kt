package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.AssessorStatsDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.BatchStatsDto
import ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto.GlobalStatsDto
import java.util.*

@RequestMapping("/stats")
@RestController
class StatsController {
    @GetMapping("/assessor/{login}")
    fun getStatsByAssessor(@PathVariable login: String): Mono<AssessorStatsDto> {
        return Mono.just(AssessorStatsDto(10, 20))
    }

    @GetMapping("/batch/{batchId}")
    fun getStatsByBatch(@PathVariable batchId: UUID): Mono<BatchStatsDto> {
        return Mono.just(BatchStatsDto(10))
    }

    @GetMapping
    fun getGlobalStats(): Mono<GlobalStatsDto> {

        return Mono.just(
            GlobalStatsDto(
                30, mapOf(
                    "Разметка поиска" to 10,
                    "Разметка поиска" to 20
                )
            )
        )
    }
}