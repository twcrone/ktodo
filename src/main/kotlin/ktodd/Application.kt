package ktodd

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ktodd.plugins.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}