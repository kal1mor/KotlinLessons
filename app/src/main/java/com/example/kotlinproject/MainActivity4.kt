package com.example.kotlinproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kotlinproject.ListViewKotlinActivity.Companion.startActivity5

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val btn = findViewById<Button>(R.id.btn1)
        btn.setOnClickListener {
            startActivity5(this)
        }

    }
    companion object{
        fun startActivity4(context: Context){
            context.startActivity(Intent(context, MainActivity4::class.java))
        }
    }
}