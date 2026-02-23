package ru.webrelab.lesson05

import kotlin.math.min

data class Product(val name: String, val weight: Int, val price: Double)

class Stock {

    private val products = mutableMapOf<Product, Int>()
    private var countProduct: Int = 2

    fun addProduct(product: Product) {
        products[product] = products.getOrDefault(product, 0) + 1
    }

    fun get(product: Product, amount: Int): Int {
        val currentAmount = products.getOrDefault(product, 0)
        val amountToReturn = min(currentAmount, amount)
        products[product] = currentAmount - amountToReturn
        return amountToReturn
    }

    override fun toString(): String {
        return products.map { (product, quantity) ->
            "${product.name} (${product.weight}g): $${product.price} $quantity items"
        }.joinToString("\n")
            .let { "** Stock **\n$it" }
    }

    fun addProduct(name: String, weight: Int, price: Double){
        addProduct(Product(name, weight, price))
    }

    operator fun invoke(arg: Stock.() -> Unit){
        arg()
    }

    infix fun String.x(weight: Int): Pair<String, Int>{
        return this to weight
    }

    infix fun Pair<String, Int>.x(by: Double){
        repeat(countProduct) {
            addProduct(first, second, by)
        }
        countProduct = 1
    }


    infix fun Int.x(name: String): String{
        countProduct = this
        return name
    }
}
fun main() {
    val stock1 = Stock()
    stock1 {
        3 x "bread" x 200 x 30.0
        "bread" x 250 x 38.0
        "apple" x 1000 x 200.0
    }
    println(stock1.toString())


}