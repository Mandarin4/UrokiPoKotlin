package com.example.uroki

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class Urok1 : AppCompatActivity() {

    private var number1: Byte = 0
    private var number2: Int = 0
    private var start: Boolean = false

    private var tvText: TextView? = null
    private var cLayout: ConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urok1)

        tvText = findViewById<TextView>(R.id.urok1_textVeiw_1)
        cLayout = findViewById<ConstraintLayout>(R.id.cLayout)

        tvText?.setText(number1.toString())

        Thread{
            start = true
            while (start){
                Thread.sleep(1000)

                runOnUiThread{
                    if (number2 == 5){
                        cLayout?.setBackgroundColor(Color.GREEN)
                        tvText?.setTextColor(Color.WHITE)
                    }
                    tvText?.setText(number2.toString())
                    number2++
                }
            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        start = false
    }



    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}