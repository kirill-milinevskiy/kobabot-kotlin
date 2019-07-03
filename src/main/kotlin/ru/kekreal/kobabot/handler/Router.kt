package ru.kekreal.kobabot.handler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(val handler: MessageHandler) {

    @Bean
    fun route() = router {
        GET("/message") { m -> handler.process(m) }
    }
}
