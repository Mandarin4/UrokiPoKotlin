package com.example.uroki.urok3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.uroki.R

class ContentActivity : AppCompatActivity() {

    var tvTitle: TextView? = null
    var tvContent: TextView? = null
    var imageVuew: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        tvTitle = findViewById(R.id.tv_urok3)
        tvContent = findViewById(R.id.tvi_urok3)
        imageVuew = findViewById(R.id.imageView2)

        tvTitle?.text = intent.getStringExtra("title")
        tvContent?.text = intent.getStringExtra("content")
        imageVuew?.setImageResource(intent.getIntExtra("image",R.drawable.ic_launcher_background))
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}