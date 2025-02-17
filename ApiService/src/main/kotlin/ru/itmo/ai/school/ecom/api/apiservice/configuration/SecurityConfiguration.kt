package ru.itmo.ai.school.ecom.api.apiservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.server.WebFilter
import ru.itmo.ai.school.ecom.api.apiservice.security.JwtReactiveAuthenticationManager
import ru.itmo.ai.school.ecom.api.apiservice.security.JwtServerAuthenticationConverter

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration(
    private val corsFilter: CorsWebFilter
) {


    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .cors { }
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers("/swagger-ui/**", "/v3/api-docs*/**").permitAll()
                    .anyExchange().authenticated()
            }
            .formLogin().disable()
            .httpBasic().disable()
            .authenticationManager(jwtReactiveAuthenticationManager())
            .addFilterAt(corsFilter, SecurityWebFiltersOrder.CORS)
            .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    fun jwtReactiveAuthenticationManager(): ReactiveAuthenticationManager {
        return JwtReactiveAuthenticationManager()
    }

    fun authenticationWebFilter(): AuthenticationWebFilter {
        val authFilter = AuthenticationWebFilter(jwtReactiveAuthenticationManager())
        authFilter.setServerAuthenticationConverter(JwtServerAuthenticationConverter())
        return authFilter
    }
}


