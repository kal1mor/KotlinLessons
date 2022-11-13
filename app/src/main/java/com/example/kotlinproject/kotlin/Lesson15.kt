package com.example.kotlinproject.kotlin

class Lesson15 {

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val first = First()
            val first2 = First()

            val second = Second
            val second2 = Second

            println("${first.hashCode()} ${first2.hashCode()}")
            println("${second.hashCode()} ${second2.hashCode()}")
        }
    }
}

class First{

    fun navigate(){
        println("navigating...")
    }

}

object Second{ //класс object используется тогда, когда класс будет много раз переиспользоваться

    fun navigate(){
        println("navigating...")
    }
}