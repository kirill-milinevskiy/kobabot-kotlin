package ru.kekreal.kobabot.handler

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MessageHandler {

    fun process(m: ServerRequest): Mono<out ServerResponse> {
        println("hello I'm kobabot")
        println(m.bodyToFlux(Update::class.java))
        return Mono.empty()
    }
}

data class Update(
    @JsonProperty("update_id")
    val updateId: String,
    val message: Message
)

data class Message(
    @JsonProperty("message_id")
    val id: Long,
    @JsonProperty("from")
    val user: TelegramUser?,
    @JsonProperty("date")
    val date: Long,
    @JsonProperty("chat")
    val chat: TelegramChat,
    @JsonProperty("text")
    val text: String?
)

data class TelegramUser(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("first_name")
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String?,
    @JsonProperty("username")
    val username: String?
)

data class TelegramChat(

    @JsonProperty("id")
    val id: Long,
    /**
     * PRIVATE, GROUP, SUPERGROUP, CHANNEL
     */
    @JsonProperty("type")
    val type: String,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("username")
    val username: String?,
    @JsonProperty("first_name")
    val firstName: String?,
    @JsonProperty("last_name")
    val lastName: String?
)
