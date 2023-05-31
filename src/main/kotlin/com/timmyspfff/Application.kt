package com.timmyspfff

import com.timmyspfff.data.user.MongoUserDataSource
import com.timmyspfff.data.user.User
import io.ktor.server.application.*
import com.timmyspfff.plugins.*
import com.timmyspfff.security.hashing.SHA256HashingService
import com.timmyspfff.security.token.JwtTokenService
import com.timmyspfff.security.token.TokenConfig
import kotlinx.coroutines.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val mongoPw = System.getenv("MONGO_PW")
    val dbName = "ktor-auth"
    val db = KMongo.createClient(
        connectionString = "mongodb+srv://timmyspfff:$mongoPw@cluster0.n6wxalh.mongodb.net/$dbName?retryWrites=true&w=majority"
    ).coroutine
        .getDatabase("ktor-auth")
    val userDataSource = MongoUserDataSource(db)
    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashingService()

//    GlobalScope.launch {
//        val user = User(
//            username = "test",
//            password = "test-password",
//            salt = "salt"
//        )
//        userDataSource.inserUser(user)
//
//    }
    configureSecurity(tokenConfig)
    configureMonitoring()
    configureSerialization()
    configureRouting(userDataSource, hashingService, tokenService, tokenConfig)
}
