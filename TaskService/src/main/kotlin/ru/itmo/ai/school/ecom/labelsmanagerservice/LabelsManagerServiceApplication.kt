package ru.itmo.ai.school.ecom.labelsmanagerservice

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration
class LabelsManagerServiceApplication

fun main(args: Array<String>) {
    runApplication<LabelsManagerServiceApplication>(*args)
}
