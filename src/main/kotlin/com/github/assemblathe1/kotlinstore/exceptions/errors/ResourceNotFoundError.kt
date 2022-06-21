package com.github.assemblathe1.kotlinstore.exceptions.errors

class ResourceNotFoundError(statusCode: Int, message: String?): Error(statusCode, message)
