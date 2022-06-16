package com.github.assemblathe1.kotlinstore.configs

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("secrets.properties")
class SecretPropertiesConfig {
}