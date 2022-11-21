package com.example.kotlinproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ListViewKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_kotlin)

        val listView = findViewById<ListView>(R.id.listView)

        val list = listOf<String>(
            "apple",
            "banana",
            "kiwi",
            "orange",
            "pineapple",
            "watermelon",
            "cherry"
        )

        val adapter = ArrayAdapter(this, R.layout.fruit_item_layout, list)
        listView.adapter = adapter
    }

    companion object{
        fun startActivity5(context: Context){
            context.startActivity(Intent(context, ListViewKotlinActivity :: class.java))
        }
    }
}