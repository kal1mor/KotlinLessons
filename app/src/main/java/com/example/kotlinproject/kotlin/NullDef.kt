package com.example.kotlinproject.kotlin

class NullDef {
    val nullable: Int? = null
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val nullDef = NullDef()
            var nonNullableValue: Int = 5 //Не нал переменная

            val nullableValue: Int? = nullDef.nullable // нал переменная


            // Первый способ проверки на нал
//            if (nullableValue != null){ //Проверка, что не нал наша нал переменная
//                nonNullableValue == nullableValue
//            }else{ //Тут на наша на переменная
//                nonNullableValue == nullableValue // нельзя присвоить нал не нал переменной
//            }

            // Второй способ проверки на нал
//            nullableValue?.let {
//                nonNullableValue = it
//            }

//            if(nullableValue != null){
//                nonNullableValue = nullableValue
//            }

            //Третий способ проверки на нал
//            nonNullableValue = nullableValue ?: 0
//            println(nonNullableValue)

            //Четверты способ проверки на нал
//            nonNullableValue = nullableValue!!
//            println(nonNullableValue)

            //Пятый способ проверки на нал
            val outer = Outer(Inner("value in inner"))
            val value: String = outer.inner?.value ?: ""
        }
    }
}

data class  Outer(val inner: Inner?)
data class Inner(val value: String)