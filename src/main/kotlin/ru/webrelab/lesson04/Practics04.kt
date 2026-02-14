package ru.webrelab.lesson04

class Distance(val meters: Int) {
    operator fun plus(other: Distance): Distance {
        val sum = this.meters + other.meters
        return Distance(sum)
    }
}

class Score(private var points: Int) {
    operator fun plusAssign(number: Int) {
        points += number
    }

    fun getPoints() {
        println(points)
    }
}

class Level(val number: Int) {
    operator fun compareTo(other: Level): Int {
        return number.compareTo(other.number)
    }
}

class User(
    val id: Int,
    val name: String
) {
    override operator fun equals(other: Any?): Boolean {
        if (other !is User) return false
        return id == other.id
    }
}

infix fun Int.with(number: Int): Int {
    return "$this$number".toInt()
}

infix fun Int.withOut(number: Int): Int {
    return "$this".replace("$number", "").toInt()
}

fun main() {
    val distance1 = Distance(2)
    val distance2 = Distance(10)
    val sumDistance = distance1 + distance2
    println(sumDistance.meters)

    val score = Score(10)
    score += 15
    score.getPoints()
    2 > 3

    val uran = Level(11)
    val rubin = Level(10)
    val result = uran >= rubin
    println(result)

    val user1 = User(1, "qqq")
    val user2 = User(1, "eeee")
    println(user2 == user1)

    println(10 with 20)
    println(1234567 withOut 2)

}