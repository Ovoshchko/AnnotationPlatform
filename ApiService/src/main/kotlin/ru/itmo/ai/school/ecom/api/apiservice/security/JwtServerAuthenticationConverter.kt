package ru.itmo.ai.school.ecom.api.apiservice.security

import org.springframework.security.core.Authentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.server.ServerWebExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import reactor.core.publisher.Mono

class JwtServerAuthenticationConverter : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        val token = getTokenFromRequest(exchange)
        return if (token != null) {
            Mono.just(UsernamePasswordAuthenticationToken(token, token))
        } else {
            Mono.empty()
        }
    }

    private fun getTokenFromRequest(exchange: ServerWebExchange): String? {
        val bearerToken = exchange.request.headers.getFirst("Authorization")
        return if (bearerToken?.startsWith("Bearer ") == true) bearerToken.substring(7) else null
    }
}