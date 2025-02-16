package ru.itmo.ai.school.ecom.api.apiservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import ru.itmo.ai.school.ecom.api.apiservice.security.JwtReactiveAuthenticationManager
import ru.itmo.ai.school.ecom.api.apiservice.security.JwtServerAuthenticationConverter

@Configuration
class SecurityConfiguration {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange { exchanges ->
                exchanges
                    .anyExchange().authenticated()
            }
            .authenticationManager(jwtReactiveAuthenticationManager())
            .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    fun jwtReactiveAuthenticationManager(): ReactiveAuthenticationManager {
        return JwtReactiveAuthenticationManager()
    }

    @Bean
    fun authenticationWebFilter(): AuthenticationWebFilter {
        val authFilter = AuthenticationWebFilter(jwtReactiveAuthenticationManager())
        authFilter.setServerAuthenticationConverter(JwtServerAuthenticationConverter())
        return authFilter
    }
}


