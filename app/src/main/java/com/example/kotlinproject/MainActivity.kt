package com.example.kotlinproject

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.kotlinproject.MainActivity2.Companion.startMainActivity2
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.red)))
        supportActionBar?.title = "kal1mor"
        supportActionBar?.setIcon(R.drawable.images)

        val btnGoToActivity2 = findViewById<Button>(R.id.btnGoToActivity2)
        btnGoToActivity2.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        val textView = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            textView.text = getString(R.string.hello_world)
            startMainActivity2(this,
                textView.text.toString() + getString(R.string.from_MainActivity))
        }
    }
}