package ru.itmo.ai.school.ecom.api.apiservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import ru.itmo.ai.school.ecom.api.apiservice.security.JwtAuthenticationWebFilter

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration(private val jwtAuthenticationWebFilter: JwtAuthenticationWebFilter) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange { exchanges ->
                exchanges.anyExchange().authenticated()
            }
            .addFilterAfter(jwtAuthenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }
}


