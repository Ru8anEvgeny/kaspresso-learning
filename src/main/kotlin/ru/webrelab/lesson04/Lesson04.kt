package ru.webrelab.lesson04

// перегрузка операторов

//Carbon — углерод — 6 протонов
//Boron — бор — 5 протонов
//Beryllium — бериллий — 4 протона
//Lithium — литий — 3 протона
//Magnesium — магний — 12 протонов

interface Nuclear

class Carbon : Nuclear
class Boron : Nuclear
class Lithium : Nuclear
class Magnesium : Nuclear
class Proton

fun Nuclear.knockOutProton(proton: Proton): Nuclear {
    return when(this) {
        is Carbon -> Boron()
        else -> throw IllegalArgumentException("Операция не осуществима")
    }
}

operator fun Nuclear.minus(proton: Proton): Nuclear {
    return when(this) {
        is Carbon -> Boron()
        else -> throw IllegalArgumentException("Операция не осуществима")
    }
}

operator fun Nuclear.times(multiplier: Int) {
    require(this is Carbon) {"Операция доступна только для углерода"}
    require(multiplier == 2) {"Доступно объединение двух ядер"}
}


// Инфиксные функции - функции которые находятся либо внутри класса либо расширяют какой-то класс
//и принимают строго 1 аргумент

infix fun String.и(other: String): Pair<String, String> {
    return this to other
}

infix fun Pair<String, String>.обсуждали(name: String): String {
    return "Два сплетника $second и $first, все знаю про $name"
}


fun main() {
    val proton = Proton()
    val carbon = Carbon()
    val boron = carbon.knockOutProton(proton)
    println(boron.javaClass.simpleName)

    println("Андрей" и "Светлана" обсуждали "Валю")

}
