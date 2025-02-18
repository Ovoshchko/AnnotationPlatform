package ru.itmo.ai.school.ecom.api.apiservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import ru.itmo.ai.school.ecom.api.apiservice.dto.response.TaskToFillDto
import ru.itmo.ai.school.ecom.api.apiservice.model.toFillDto
import ru.itmo.ai.school.ecom.api.apiservice.service.TaskRedisService
import java.util.*

@RestController
@RequestMapping("/v1/task")
class TaskController(
    private val taskRedisService: TaskRedisService
) {

    @GetMapping
    fun getTask(exchange: ServerWebExchange, @RequestParam project: UUID = UUID.randomUUID()): Mono<ResponseEntity<TaskToFillDto>> {
        return taskRedisService.getTask(project, exchange.getAttribute("owner") ?: "basic")
            .map { ResponseEntity.ok(it.toFillDto()) }
            .defaultIfEmpty(ResponseEntity.noContent().build())
    }
}

