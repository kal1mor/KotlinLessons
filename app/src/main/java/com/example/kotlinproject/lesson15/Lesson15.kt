package com.example.kotlinproject.lesson15


private const val ZERO = 0

fun outerFun(){// Внешняя функция
    println("I'm an outer fun")
}

class Lesson15 {
    val country: String = "Belarus"
    var city = "Minsk"
    lateinit var address: String


    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println("hello world")
            val kotlinClass = KotlinClass("kal1mor", 15)
            val kotlinClass2 = KotlinClass("belarus", "minsk", "serafimovicha")
            val string = kotlinClass.getNameAndAge();
            println(string)
            print("${kotlinClass2.country} ${kotlinClass2.city} ${kotlinClass2.address}")
            val lesson15 = Lesson15();
            //  lesson15.country = "" //не можем изменить значение т.к. переменная val, которой уже присвоено значение
            lesson15.city = "Brest"
            lesson15.address = "serafimovicha"
            println("\n${lesson15.address}")
            lesson15.returnBoolean()
            outerFun()
        }
    }
    fun emptyFun(): Unit{// Функция которая ни чего не возвращает
        println("I'm an empty fun")
    }

    fun returnBoolean(): Boolean = true // Функция которая фовращает boolean

}

class KotlinClass(val name: String, var age: Int): Parent(), Behavior {

    public var country: String = ""
    public var city: String = ""
    public var address: String = ""

    constructor() : this("", 1)

    constructor(country: String, city: String, address: String) : this(){
        this.country = country
        this.city = city
        this.address = address
    }

    fun getNameAndAge(): String{
        age = 16
        return "$name $age"
    }

    override fun getHairColor() {
        super.getHairColor()
    }

    override fun getEyeColor() {
        super.getEyeColor()
    }
}

open class Parent{

    open fun getHairColor(){

    }
}

interface Behavior{

    fun getEyeColor(){

    }
}
