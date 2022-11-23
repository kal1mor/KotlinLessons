package com.example.kotlinproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.kotlinproject.MainActivity4.Companion.startActivity4

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        val lambda = {string:String -> Toast.makeText(this, "Your text is $string", Toast.LENGTH_SHORT).show()}
        lambda("Set text in lambda")

        val btnClickMe = findViewById<Button>(R.id.btnClickMe)

        val person = object{
            val name = "kalimor"
            fun develop(){
                Log.w("anonym class", "called develop fun")
            }
        }

        val person2 = object : Developer{
            val name = "kalimor"
            override fun develop() {
                Log.w("anonym class", "called develop fun DEVELOPER")
            }
        }

        val house = HouseBuilder.Builder
            .setStock(3)
            .setSwimpool(false)
            .build()

        btnClickMe.setOnClickListener {
            startActivity4(this)
            Log.w("house builder", "${house.hasSwimpool()} ${house.howManyStocks()}" )
            person.develop()
            person2.name
            person2.develop()
            callAnonymClass(person2)
            callAnonymClass(object : Developer{
                override fun develop() {
                    Log.w("anonym class", "called develop fun DEVELOPER2")
                }
            })
            Toast.makeText(this, person.name, Toast.LENGTH_SHORT).show()

        }

        makeRequest("https://www.google.com/"){ callbackResult ->
            Log.w("callback result", callbackResult)
        }
        returnAnonymClass("set string in return fun").develop()

    }

    fun get(): String {
        return ""
    }

    fun get2() = ""

    private fun returnAnonymClass(string: String) = object {
        fun develop(){
            Log.w("anonym class", "called develop fun returnType $string")
        }
    }

    fun callAnonymClass(developer: Developer){
        developer.develop()

    }

    fun makeRequest(url:String, argForCallBack: (string: String) -> Unit){
        argForCallBack.invoke("callbackResult $url")
    }

    companion object{

        fun kotlinActivityStart(context: Context){
            context.startActivity(Intent(context, KotlinActivity::class.java))
        }

    }
}

interface Developer{
    fun develop(){

    }
}