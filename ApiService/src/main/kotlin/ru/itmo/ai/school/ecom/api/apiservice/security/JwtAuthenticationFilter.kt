package ru.itmo.ai.school.ecom.api.apiservice.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class JwtAuthenticationFilter(
    private val authenticationManager: JwtReactiveAuthenticationManager
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = extractToken(exchange) ?: return chain.filter(exchange)

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(token, token))
            .flatMap { auth ->
                val username = auth.name

                exchange.attributes["owner"] = username

                chain.filter(exchange)
            }
    }

    private fun extractToken(exchange: ServerWebExchange): String? {
        val bearerToken = exchange.request.headers.getFirst("Authorization")
        return if (bearerToken?.startsWith("Bearer ") == true) bearerToken.substring(7) else null
    }
}
