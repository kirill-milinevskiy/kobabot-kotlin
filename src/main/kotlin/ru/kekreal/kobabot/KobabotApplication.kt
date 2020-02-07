package ru.kekreal.kobabot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class KobabotApplication

fun main(args: Array<String>) {
    runApplication<KobabotApplication>(*args)
}
