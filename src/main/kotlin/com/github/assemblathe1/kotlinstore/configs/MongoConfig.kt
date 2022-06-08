package com.github.assemblathe1.kotlinstore.configs

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.util.*

//@Configuration
//@EnableMongoRepositories("com.github.assemblathe1.kotlinstore.repositories")
//class MongoConfig : AbstractMongoClientConfiguration() {
//    override fun getDatabaseName(): String {
//        return "onlinestore"
//    }
//
//    override fun mongoClient(): MongoClient {
//        val connectionString = ConnectionString("mongodb://localhost:27017/onlinestore")
//        val mongoClientSettings = MongoClientSettings.builder()
//            .applyConnectionString(connectionString)
//            .build()
//        return MongoClients.create(mongoClientSettings)
//    }
//
//    public override fun getMappingBasePackages(): MutableCollection<String> {
//        return Collections.singleton("com.github.assemblathe1.kotlinstore")
//    }
//}