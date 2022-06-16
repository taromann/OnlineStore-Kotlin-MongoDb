package com.github.assemblathe1.kotlinstore.exceptions.errors

class AuthError(statusCode: Int, message: String): Error(statusCode, message)
