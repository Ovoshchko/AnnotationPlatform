package ru.itmo.ai.school.ecom.api.apiservice.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import reactor.core.publisher.Mono

class JwtReactiveAuthenticationManager : ReactiveAuthenticationManager {
    private val secretKey = "secretsecretsecretsecretsecretsecret".toByteArray()

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials as String

        return Mono.justOrEmpty(parseToken(token))
            .map { claims ->
                val username = claims["name"]?.toString() ?: ""
                val taskTypeAccess = claims["task_type_access"] as? List<String> ?: listOf()
                val authorities = taskTypeAccess.map { SimpleGrantedAuthority(it) }

                UsernamePasswordAuthenticationToken(username, token, authorities)
            }
    }

    private fun parseToken(token: String): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            null
        }
    }
}

