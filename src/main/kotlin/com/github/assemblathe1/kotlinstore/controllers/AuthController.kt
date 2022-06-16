package com.github.assemblathe1.kotlinstore.controllers

import com.github.assemblathe1.kotlinstore.dto.JwtRequest
import com.github.assemblathe1.kotlinstore.dto.JwtResponse
import com.github.assemblathe1.kotlinstore.exceptions.errors.AuthError
import com.github.assemblathe1.kotlinstore.services.UserService
import com.github.assemblathe1.kotlinstore.utils.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthControllerKotlin @Autowired constructor(
    val userService: UserService,
    val jwtTokenUtil: JwtTokenUtil,
    val authenticationManager: AuthenticationManager
) {


    @PostMapping("/auth")
    fun createAuthToken(@RequestBody authRequest: JwtRequest): ResponseEntity<Any?> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    authRequest.username,
                    authRequest.password
                )
            )
        } catch (e: Exception) {
            return ResponseEntity(
                AuthError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password ${e}"),
                HttpStatus.UNAUTHORIZED
            )
        }
        val userDetails: UserDetails = userService.loadUserByUsername(authRequest.username)
        val token: String = jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(JwtResponse(token))
    }
}