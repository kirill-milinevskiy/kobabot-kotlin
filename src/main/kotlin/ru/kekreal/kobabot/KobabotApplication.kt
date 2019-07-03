package ru.kekreal.kobabot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KobabotApplication

fun main(args: Array<String>) {
	runApplication<KobabotApplication>(*args)
}
