package ru.itmo.ai.school.ecom.labelsmanagerservice.answerservice.dto

import kotlin.time.Duration

data class GlobalStatsDto(
    val batchesInProgress: Int = 0,
    val batchesInProgressByType: Map<String, Int> = emptyMap(),
    val timeLeftEstimation: Duration = Duration.ZERO,
)
