package com.github.assemblathe1.kotlinstore

import com.github.assemblathe1.kotlinstore.entities.Product
import com.github.assemblathe1.kotlinstore.entities.User
import com.github.assemblathe1.kotlinstore.repositories.ProductRepository
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.springframework.data.mongodb.core.MongoTemplate

fun main() {

    val pojoCodecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
        MongoClientSettings.getDefaultCodecRegistry(),
        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
    )

    val connectionString = ConnectionString("mongodb://localhost:27017")
    val mongoClientSettings = MongoClientSettings
        .builder()
        .codecRegistry(pojoCodecRegistry)
        .applyConnectionString(connectionString)
        .build()
    val mongoClient: MongoClient = MongoClients.create(mongoClientSettings)
    val database = mongoClient.getDatabase("onlinestore")

    val collectionOfProducts: MongoCollection<Product> = database.getCollection("products", Product::class.java)

    collectionOfProducts.insertMany(mutableListOf(
        Product(title = "Chicken", price = 20.toBigDecimal()),
        Product(title = "Bread", price = 40.toBigDecimal()),
        Product(title = "Cheese", price = 60.toBigDecimal()),
        Product(title = "Beef", price = 30.toBigDecimal()),
        Product(title = "Milk", price = 10.toBigDecimal()),
        Product(title = "Salmon", price = 15.toBigDecimal()),
        Product(title = "Trout", price = 76.toBigDecimal()),
        Product(title = "Avocado", price = 81.toBigDecimal()),
        Product(title = "Carrot", price = 34.toBigDecimal()),
        Product(title = "Cucumber", price = 105.toBigDecimal()),
        Product(title = "Apple", price = 777.toBigDecimal())

    ))

    val collectionOfUsers: MongoCollection<User> = database.getCollection("users", User::class.java)

    collectionOfUsers.insertMany(mutableListOf(
        User(userName = "John", age = 15, email = "john@mail.ru"),
        User(userName = "Nick", age = 25, email = "nick@mail.ru"),
        User(userName = "Roma", age = 46, email = "roma@mail.ru"),
        User(userName = "Fill", age = 17, email = "fill@mail.ru"),
        User(userName = "Anya", age = 89, email = "anya@mail.ru"),
        User(userName = "Masha", age = 101, email = "masha@mail.ru")
    ))

}