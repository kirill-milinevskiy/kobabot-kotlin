package ru.kekreal.kobabot.handler

import com.fasterxml.jackson.annotation.JsonProperty
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.lang.System.getenv

private val log = KotlinLogging.logger {}

@Component
class Sender {
    private val tgAccessKey = getenv("BOT_ACCESS_KEY")
    private val tgClient = WebClient.create("https://api.telegram.org/bot$tgAccessKey")

    fun sendBeer(update: Update): Mono<Any> {
        println("..............................sending.beer..............................")
        val reqMono = SendStickerRequest(
            stickerId = "CAACAgIAAxkBAAMTXkEaBTI6HOIvDYY7C8tbwD8JgNEAAtcAA4RlBwqVO9Okhj0CJhgE",
            chatId = update.message.chat.id,
            replyToMessageId = update.message.id
        ).also(::println).toMono()

        return tgClient.post()
            .uri("/sendSticker")
            .body(reqMono, SendStickerRequest::class.java)
            .exchange()
            .doOnNext {
                println("..................................done..................................")
            }
            .flatMap { it.bodyToMono(Any::class.java) }
    }
}

data class SendStickerRequest(
    @JsonProperty("chat_id")
    private val chatId: Long,
    @JsonProperty("file_id")
    private val stickerId: String,
    @JsonProperty("reply_to_message_id")
    private val replyToMessageId: Long? = null
)
