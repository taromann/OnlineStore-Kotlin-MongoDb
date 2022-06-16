package com.github.assemblathe1.kotlinstore.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenUtil {

    @Value("\${jwt.secret}")
    public val secret: String = ""

    @Value("\${jwt.lifetime}")
    val jwtLifetime: Int = 0


    fun generateToken(userDetails: UserDetails): String {
        val claims = mutableMapOf<String, Any>()
        val rolesList = userDetails.authorities
            .map { it.authority }
            .toList()
        claims["roles"] = rolesList

        val issuedDate = Date()
        val expiredDate = Date(issuedDate.time + jwtLifetime)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(issuedDate)
            .setExpiration(expiredDate)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun getUsernameFromToken(token: String): String = getClaimFromToken<String>(token) { it.subject }

    fun getRoles(token: String): MutableList<String> =
        getClaimFromToken(token) { it.get("roles", mutableListOf<String>().javaClass) }


    private fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T =
        claimsResolver(getAllClaimsFromToken(token))


    private fun getAllClaimsFromToken(token: String): Claims =
        Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body

}




