package ru.itmo.ai.school.ecom.api.apiservice.security
//
//import io.jsonwebtoken.Claims
//import io.jsonwebtoken.Jwts
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.core.userdetails.User
//import org.springframework.stereotype.Component
//import org.springframework.web.server.ServerWebExchange
//import org.springframework.web.server.WebFilter
//import org.springframework.web.server.WebFilterChain
//import reactor.core.publisher.Mono
//
//@Component
//class JwtAuthenticationWebFilter : WebFilter {
//    private val secretKey = "secretsecretsecretsecretsecretsecret".toByteArray()
//
//    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
//        val token = getTokenFromRequest(exchange)
//
//        if (token != null) {
//            val claims = parseToken(token)
//            if (claims != null) {
//                val username = claims["name"]?.toString() ?: ""
//                val taskTypeAccess = claims["task_type_access"] as? List<String> ?: listOf()
//                val authorities = taskTypeAccess.map { SimpleGrantedAuthority(it) }
//                val auth = JwtAuthenticationToken(username, token, authorities)
//                SecurityContextHolder.getContext().authentication = auth
//            }
//        }
//
//        return chain.filter(exchange)
//    }
//
//    private fun getTokenFromRequest(exchange: ServerWebExchange): String? {
//        val bearerToken = exchange.request.headers.getFirst("Authorization")
//        return if (bearerToken?.startsWith("Bearer ") == true) bearerToken.substring(7) else null
//    }
//
//    private fun parseToken(token: String): Claims? {
//        return try {
//            Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .body
//        } catch (e: Exception) {
//            null
//        }
//    }
//}
