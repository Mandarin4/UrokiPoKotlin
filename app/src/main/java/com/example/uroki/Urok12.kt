package com.example.uroki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uroki.databinding.ActivityUrok11Binding
import com.example.uroki.databinding.ActivityUrok12Binding

class Urok12 : AppCompatActivity() {
    private lateinit var binding: ActivityUrok12Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrok12Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}