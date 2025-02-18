package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import ru.itmo.ai.school.ecom.api.apiservice.dto.answer.FilledTaskDto
import ru.itmo.ai.school.ecom.api.apiservice.dto.answer.FilledTaskRequestDto
import ru.itmo.ai.school.ecom.api.apiservice.service.AnswerService

@RestController
@RequestMapping("/v1/answer")
class AnswerController(
    private val answerService: AnswerService,
) {
    @PostMapping
    fun saveFilledTask(exchange: ServerWebExchange, @RequestBody filledTaskRequestDto: FilledTaskRequestDto) {
        val filledBy = (exchange.attributes["owner"] ?: throw RuntimeException("Empty assessor")) as String
        val filledTaskDto = FilledTaskDto(
            taskId = filledTaskRequestDto.taskId,
            filledBy = filledBy,
            answer = filledTaskRequestDto.answer, metadata = filledTaskRequestDto.metadata,
            isHoneypot = filledTaskRequestDto.isHoneypot
        )
        answerService.sendFilledTask(filledTaskDto)
    }
}