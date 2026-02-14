package ru.webrelab.lesson04.homework04

import javax.print.attribute.standard.MediaSize.Other
import kotlin.random.Random
import kotlin.time.times

//Доступ к элементу по индексу ([ ]) и проверка наличия через in
//Есть класс Inventory, внутри которого хранится список строк items.
//Перегрузи оператор + чтобы добавлять новые элементы в список.
//Перегрузи оператор [ ], чтобы получать предмет по индексу.
//Перегрузи оператор in, чтобы проверять вхождение строки в список items.

class Inventory() {
    private val items = mutableListOf<String>()
    operator fun plus(element: String) {
        items.add(element)
    }

    operator fun get(element: Int ): String {
        return items[element]
    }

    operator fun contains(element: String): Boolean {
        return element in items
    }

    fun getItems(): List <String> {
        return items
    }

}

//Инверсия состояния (!)
//Есть класс Toggle с полем enabled: Boolean.
//Перегрузи оператор !, чтобы он возвращал новый объект с противоположным состоянием.

class Toggle(private val enabled: Boolean) {
    operator fun not(): Toggle {
        return Toggle(!enabled)
    }

    override fun toString(): String {
        return enabled.toString()
    }
}

//Умножение значения (*)
//Есть класс Price с полем amount: Int.
//Перегрузи оператор *, чтобы можно было умножать цену на целое число (например, количество товаров).

class Price( val amount: Int ) {
    operator fun times(items: Int): Int {
        return amount * items
    }
}

//Диапазон значений (..)
//Есть класс Step с полем number: Int.
//Перегрузи оператор .., чтобы можно было создавать диапазон шагов между двумя объектами Step.
//Сделай возможной проверку: входит ли один Step в диапазон шагов с помощью оператора in.
// Обрати внимание, что это обратная операция и нужно расширять класс IntRange для проверки вхождения в
// него Step.

class Step( val number: Int) {
    operator fun rangeTo(other: Step): IntRange {
        return number..other.number
    }

    operator fun IntRange.contains(step: Step): Boolean {
        return step.number in this
    }


}

//Последовательное объединение (+)
//Есть класс Log с полем entries: List<String>.
//Перегрузи оператор +, чтобы при сложении логов записи объединялись в один лог.

class Log() {
    private val entries = mutableListOf<String>()
    operator fun plus(entry: String): Log {
        entries.add(entry)
        return this
    }

    fun printLog() {
        println(entries.toString())
    }
}

//Инфиксные функции
//Генератор фраз.
//Используй класс Person из "общих рекомендаций" ниже. Добавь в этот класс три инфиксные функции:
//says должна принимать строку, добавлять её в список фраз и возвращать этот же объект Person для дальнейшей
// работы. Всегда вызывается первой.
//and работает так же как и says, но не может быть вызвана первой (в этом случае нужно выкидывать
// IllegalStateException).
//or должна принимать строку и заменять последнюю фразу в списке фраз, выбирая случайным образом переданную
// строку или последнюю фразу из списка фраз с помощью метода selectPhrase. Так же должна возвращать текущий
// объект Person для дальнейшей работы. Так же не может быть вызвана первой, иначе выбрасывает
// IllegalStateException.


class Person(private val name: String) {

    private val phrases = mutableListOf<String>()

    fun print() {
        println(phrases.joinToString(" "))
    }

    private fun selectPhrase(first: String, second: String): String {
        val random = Random.nextInt(0, 2)
        return if (random == 0) first else second
    }

    infix fun says(text: String): Person {
        phrases.add(text)
        return this
    }

    infix fun and(text: String): Person {
        check(phrases.isNotEmpty()){"Эта функция не может быть вызвана первой"}
        phrases.add(text)
        return this
    }

    infix fun or(text: String): Person {
        check(phrases.isNotEmpty()) {"Эта функция не может быть вызвана первой"}
        phrases[phrases.lastIndex] = selectPhrase(phrases[phrases.lastIndex], text)
        return this
    }
}

fun main() {
    val inventory = Inventory()
    inventory + "3"
    inventory + "5"
    inventory + "10"
    println(inventory.getItems())
    println(inventory[1])
    println("11" in inventory)

    val toggle = Toggle(true)
    println(!toggle)

    val price = Price(10)
    println(price * 2)

    val log = Log()
    log + "1" + "2" + "33" + "47"
    log.printLog()

    val person = Person("Evgeny")
    person says "Привет" and "Как дела?" or "Что нового?" or "Как жизнь?"
    person.print()


}