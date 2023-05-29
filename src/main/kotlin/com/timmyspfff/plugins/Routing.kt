package com.timmyspfff.plugins

import com.timmyspfff.authenticate
import com.timmyspfff.data.user.UserDataSource
import com.timmyspfff.getSecretInfo
import com.timmyspfff.security.hashing.HashingService
import com.timmyspfff.security.token.TokenConfig
import com.timmyspfff.security.token.TokenService
import com.timmyspfff.signIn
import com.timmyspfff.signUp
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
