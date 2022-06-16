package com.github.assemblathe1.kotlinstore
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories("com.github.assemblathe1.kotlinstore.repositories")
class KotlinStoreApplication

fun main(args: Array<String>) {
    runApplication<KotlinStoreApplication>(*args)
}
