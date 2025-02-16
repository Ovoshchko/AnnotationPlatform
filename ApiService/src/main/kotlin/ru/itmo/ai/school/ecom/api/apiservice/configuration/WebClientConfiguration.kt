package ru.itmo.ai.school.ecom.api.apiservice.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${batch.service.url}") private val batchServiceUrl: String,
) {

    @Bean
    @Qualifier("batchWebClient")
    fun batchWebClient(builder: WebClient.Builder): WebClient {
        return builder.baseUrl(batchServiceUrl)
            .build()
    }
}
