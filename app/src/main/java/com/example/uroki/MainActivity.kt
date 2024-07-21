package com.example.uroki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.uroki.urok3.Urok3
import com.example.uroki.urok7.Urok7

class MainActivity : AppCompatActivity() {
    private var backPressedTime = 0L

    //https://www.youtube.com/watch?v=MjXS7xImZLw&list=PLmjT2NFTgg1clSDgx1YYOuVyZuCXVjfuR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickButton1 = findViewById<Button>(R.id.main_button_1)

        clickButton1.setOnClickListener{
            val intent = Intent(this, Urok1::class.java)
            startActivity(intent)
        }
    }


    fun onClickButton2(view: View){
        val intent = Intent(this, Urok2::class.java)
        startActivity(intent)
    }

    fun onClickButton3(view: View){
        val intent = Intent(this, Urok3::class.java)
        startActivity(intent)
    }

    fun onClickButton4(view: View){
        val intent = Intent(this, Urok4::class.java)
        startActivity(intent)
    }

    fun onClickButton5(view: View){
        val intent = Intent(this, Urok5::class.java)
        startActivity(intent)
    }

    fun onClickButton6(view: View){
        val intent = Intent(this, Urok6::class.java)
        startActivity(intent)
    }

    fun onClickButton7(view: View){
        val intent = Intent(this, Urok7::class.java)
        startActivity(intent)
    }

    fun onClickButton8(view: View){
        val intent = Intent(this, Urok8::class.java)
        startActivity(intent)
    }

    fun onClickButton9(view: View){
        val intent = Intent(this, Urok9::class.java)
        startActivity(intent)
    }

    fun onClickButton10(view: View){
        val intent = Intent(this, Urok10::class.java)
        startActivity(intent)
    }

    fun onClickButton11(view: View){
        val intent = Intent(this, Urok11::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        } else {
            Toast.makeText(applicationContext, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}