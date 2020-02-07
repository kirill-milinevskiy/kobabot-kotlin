package ru.kekreal.kobabot.handler

import com.fasterxml.jackson.annotation.JsonProperty
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

private val log = KotlinLogging.logger {}

@Component
class UpdateHandler(private val sender: Sender) {

    fun handle(update: ServerRequest): Mono<Any> =
        update.bodyToMono(Update::class.java)
            .doOnNext(::println)
            .filter {
                it.message.text != null &&
                    (it.message.text.contains("пивко", ignoreCase = true) ||
                        it.message.text.contains("пивка", ignoreCase = true) ||
                        it.message.text.contains("пивку", ignoreCase = true))
            }
            .flatMap(sender::sendBeer)

    fun process(update: Update): Update {
        return update.also(::println)
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
    val chat: Chat,
    @JsonProperty("text")
    val text: String?,
    @JsonProperty("sticker")
    val sticker: Sticker?
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

data class Chat(
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

data class Sticker(
    @JsonProperty("file_id")
    val id: String,
    @JsonProperty("emoji")
    val emoji: String?
)
