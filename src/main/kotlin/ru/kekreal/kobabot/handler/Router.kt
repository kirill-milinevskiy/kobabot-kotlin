package ru.kekreal.kobabot.handler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(val handler: UpdateHandler) {

    @Bean
    fun route() = router {
        POST("/message") {
            ServerResponse.ok().body(handler.handle(it), Any::class.java)
        }
    }
}
