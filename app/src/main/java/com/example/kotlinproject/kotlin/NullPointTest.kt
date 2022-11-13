package com.example.kotlinproject.kotlin

class NullPointTest {

    val number: Int? = null
    val name: String? = null

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val values = Values(0)

            println(values.name)
        }
    }
}

data class  Values(
    val number: Int,
    val name: String? = "have no string"
)