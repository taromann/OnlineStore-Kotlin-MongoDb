package com.github.assemblathe1.kotlinstore.configs

import com.github.assemblathe1.kotlinstore.utils.JwtTokenUtil

import io.jsonwebtoken.ExpiredJwtException
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtRequestFilter @Autowired constructor(val jwtTokenUtil: JwtTokenUtil) : OncePerRequestFilter() {

    @Override
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")
        var username: String? = null
        var jwt: String? = null
        authHeader?.let {
            if (it.startsWith("Bearer ")) {
                jwt = it.substring(7)
                try {
                    username = jwtTokenUtil.getUsernameFromToken(jwt!!)
                } catch (e: ExpiredJwtException) {
                    KotlinLogging.logger {}.warn { e.message }
                }
            }
        }

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val token = UsernamePasswordAuthenticationToken(
                username,
                null,
                jwt?.let { it -> jwtTokenUtil.getRoles(it).map { SimpleGrantedAuthority(it) }.toList() }
            )
            SecurityContextHolder.getContext().authentication = token
        }
        filterChain.doFilter(request, response)
    }
}
