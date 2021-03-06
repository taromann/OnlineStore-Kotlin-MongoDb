package com.github.assemblathe1.kotlinstore


import com.geekbrains.spring.web.entities.Product
import com.github.assemblathe1.kotlinstore.dto.Cart
import com.github.assemblathe1.kotlinstore.entities.Order
import com.github.assemblathe1.kotlinstore.entities.OrderItem
import com.github.assemblathe1.kotlinstore.entities.Role
import com.github.assemblathe1.kotlinstore.entities.User
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import java.math.BigDecimal

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

//    val collectionOfProducts: MongoCollection<Product> = database.getCollection("products", Product::class.java)
//
//    collectionOfProducts.insertMany(mutableListOf(
//        Product(title = "Chicken", price = 25.toBigDecimal()),
//        Product(title = "Bread", price = 48.toBigDecimal()),
//        Product(title = "Cheese", price = 60.toBigDecimal()),
//        Product(title = "Beef", price = 30.toBigDecimal()),
//        Product(title = "Milk", price = 10.toBigDecimal()),
//        Product(title = "Salmon", price = 15.toBigDecimal()),
//        Product(title = "Trout", price = 76.toBigDecimal()),
//        Product(title = "Avocado", price = 81.toBigDecimal()),
//        Product(title = "Carrot", price = 34.toBigDecimal()),
//        Product(title = "Cucumber", price = 105.toBigDecimal()),
//        Product(title = "Apple", price = 777.toBigDecimal())
//
//    ))

    val collectionOfUsers: MongoCollection<User> = database.getCollection("users", User::class.java)

//    collectionOfUsers.insertMany(mutableListOf(
//        User(userName = "John", age = 15, email = "john@mail.ru", password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i", roles = mutableSetOf("user", "admin")),
//        User(userName = "Nick", age = 25, email = "nick@mail.ru", password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i"),
//        User(userName = "Roma", age = 46, email = "roma@mail.ru", password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i"),
//        User(userName = "Fill", age = 17, email = "fill@mail.ru", password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i"),
//        User(userName = "Anya", age = 89, email = "anya@mail.ru", password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i"),
//        User(userName = "Masha", age = 101, email = "masha@mail.ru", password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i")
//    ))

    val collectionOfRoles: MongoCollection<Role> = database.getCollection("roles", Role::class.java)

//    collectionOfRoles.insertMany(mutableListOf(
//        Role(name = "user"),
//        Role(name = "admin")
//
//    ))

    val collectionOfCarts: MongoCollection<Cart> = database.getCollection("carts", Cart::class.java)
    val product = Product(title = "Chicken1", price = BigDecimal(25))

//    collectionOfCarts.insertOne(
//        Cart(
//            items = mutableListOf(OrderItemDto(productId = product.id.toString(), productTitle = product.title, quantity = 10, pricePerProduct = product.price, price = product.price)),
//            totalPrice = BigDecimal(777)
//        )
//    )

//    collectionOfUsers.insertOne(
//        User(
//            userName = "Rombario",
//            age = 15,
//            email = "john@mail.ru",
//            password = "\$2a\$04\$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i",
//            roles = mutableSetOf("user", "admin"),
//            cart =  Cart(
//                items = mutableListOf(
//                    OrderItemDto(
//                        productId = product.id.toString(),
//                        productTitle = product.title,
//                        quantity = 10,
//                        pricePerProduct = product.price,
//                        price = product.price
//                    )
//                ),
//                totalPrice = BigDecimal(777)
//            )
//        ),
//    )

    val collectionOfOrders: MongoCollection<Order> = database.getCollection("orders", Order::class.java)

    collectionOfOrders.insertMany(
        mutableListOf(
            Order(
                username = "Rombario",
                items = mutableListOf(
                    OrderItem(
                        product = product,
                        quantity = 10,
                        pricePerProduct = product.price,
                        price = product.price
                    )
                ),
                address = "Sverdlovsk",
                phone = "8-800-555-3535"
            )

        )
    )


}