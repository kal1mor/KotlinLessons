package com.example.kotlinproject.kotlin

class ObjectClass {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val third = Third().navigate()
            val four = Third.walk()
        }
    }
}

class Third{
    fun navigate(){
        println("navigation")
    }

    companion object{
        fun walk(){
            println("I'm walking")
        }
    }
}