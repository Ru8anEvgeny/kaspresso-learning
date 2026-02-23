package ru.webrelab.lesson05

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.max

val r = max(2, 3)
operator fun String.invoke(arg: String) {
    println("$this: $arg")
}

fun String.halve(): String {
    return substring(0, length / 2)
}

val halve: String.() -> String = {
    substring(0, length/2)
}

object Commands {
    fun execute(command: String) {
        Thread.sleep(100)
    }
}

operator fun String.invoke(fnc: String.() -> String): String {
    return fnc()
}

operator fun String.invoke() {
    val time = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss.SSS"))
    println("$time - $this")
    Commands.execute(this)
}

object Server {

    operator fun invoke(fnc: Server.() -> Unit) {
        fnc()
    }

    fun start() {
        "start server"()
    }

    fun loadOs() {
        "load operation system"()
    }

    fun login() {
        "login by default user"()
    }
}

fun main() {
    "Kotlin"("forever")
    Server {
        start()
        loadOs()
        login()
    }
}
