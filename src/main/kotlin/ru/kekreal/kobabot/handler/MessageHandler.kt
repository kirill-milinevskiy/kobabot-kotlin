package ru.kekreal.kobabot.handler

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MessageHandler {

    fun process(m: ServerRequest): Mono<out ServerResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
