package com.example.kotlinlessons

import android.provider.UserDictionary.Words

class CollectionKotlin {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            //Не изменяемая коллекция
            val collectionList = listOf<String>(
                "hello",
                "bye",
                "world")
            //collectionList.add() нельзя добавить в конструктор если коллекия не изменяемая

            //Изменяемая коллекция
            val collectionKorlin2 = mutableListOf<String>()
            collectionKorlin2.add("hello")
            collectionKorlin2.add("bye")
            collectionKorlin2.add("world")

            collectionList.forEach {words ->
                println("$words")
            }

            for(i in 1..collectionKorlin2.size){
                println(i)
            }

            val arrayList = arrayListOf<String>(
                "Hello",
                "World",
                "Bye"
            )

            for (word in arrayList){
                println(word)
            }

            for (i in 10 downTo  1 step 2){
                println("$i")
            }

            for(i in 1 .. 10){
                if (i == 2){
                    println("Hello world!")
                    continue
                }
                println(i)
                println("World is broken")
            }
            val collectionKotlin = CollectionKotlin();

            repeat(10){
                println(collectionKotlin.getName())
            }

        }
    }
    fun getName(): String{
        return "Hello!!!"
    }
}