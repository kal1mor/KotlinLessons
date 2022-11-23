package com.example.kotlinproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kotlinproject.KotlinActivity.Companion.kotlinActivityStart
import com.google.android.material.textfield.TextInputLayout

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val editText = findViewById<EditText>(R.id.tet_text)
        val editText2 = findViewById<EditText>(R.id.tet_text2)
        val btnDisplayText1 = findViewById<Button>(R.id.btn_displayText1)
        val txtView = findViewById<TextView>(R.id.tv_view1)

        val layout1 = findViewById<TextInputLayout>(R.id.textInputLayout1)
        val layout2 = findViewById<TextInputLayout>(R.id.textInputLayout2)

        val rb1 = findViewById<RadioButton>(R.id.rb1)
        val rb2 = findViewById<RadioButton>(R.id.rb2)

        rb1.setOnClickListener {
            if (rb1.isChecked){
                rb2.isChecked = false
            }else{
                rb1.isChecked = true
            }

        }

        rb2.setOnClickListener {
            if (rb2.isChecked){
                rb1.isChecked = false
            }else{
                rb2.isChecked = true
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setTitle("Information")
            .setMessage("I am Android Developer")
            .setCancelable(false)
            .setPositiveButton("Ok"){dialog, _ ->
                Toast.makeText(this, "called positive", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel"){dialog, _ ->
                dialog.cancel()
            }


        btnDisplayText1.setOnClickListener {
            dialog.show()
            if(editText2.text.toString().isEmpty()){
                layout1.setErrorIconDrawable(R.drawable.ic_error)
                editText.error = "email cant be empty"
                layout1.error = "email cant be empty"
            }else if (editText2.text.toString().isEmpty()){
                layout2.setErrorIconDrawable(R.drawable.ic_error)
                editText2.error = "password cant be empty"
            }else{
                kotlinActivityStart(this)
                txtView.text = "${editText.text.toString()} ${editText2.text.toString()}"
            }
        }

        btnDisplayText1.setOnClickListener {
            txtView.text = "${editText.text.toString()} ${editText2.text.toString()}"
        }
    }
}


