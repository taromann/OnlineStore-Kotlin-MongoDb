package com.github.assemblathe1.kotlinstore.exceprions.exceptions

import java.util.stream.Collectors

class ValidationException(val errorFieldsMessages: List<String?>) : RuntimeException(errorFieldsMessages.stream().collect(Collectors.joining(", ")))