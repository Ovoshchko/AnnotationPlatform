package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import ru.itmo.ai.school.ecom.api.apiservice.dto.answer.FilledTaskDto
import ru.itmo.ai.school.ecom.api.apiservice.service.AnswerService

@RestController("/v1/answer")
class AnswerController(
    private val answerService: AnswerService,
) {
    @PostMapping
    fun saveFilledTask(exchange: ServerWebExchange, @RequestBody filledTaskDto: FilledTaskDto) {
        val filledBy = (exchange.attributes["owner"] ?: throw RuntimeException("Empty assessor")) as String
        filledTaskDto.filledBy = filledBy
        answerService.sendFilledTask(filledTaskDto)
    }
}