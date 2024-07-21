package com.example.uroki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.uroki.databinding.ActivityUrok11Binding
import com.example.uroki.databinding.ActivityUrok8Binding

class Urok11 : AppCompatActivity() {
    private lateinit var binding: ActivityUrok11Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUrok11Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTextUrok11.text = "Введите размерность доски"
    }
    
    fun btnOtrisovka(view: View){
        binding.tvTextUrok11.text = ""
        if (binding.edTextUrok11.text.toString().equals("")){
            binding.tvTextUrok11.text = "Введите размерность доски"
        }
        else{
            otrisovkaShahmat(binding.edTextUrok11.text.toString().toInt())
        }
    }

    private fun otrisovkaShahmat(n: Int){
        var flag = true
        val simbol1 = "*"
        val simbol2 = "+"

        for(stroka in 0..n-1){
            for (stolbec in 0..n-1){
                if (flag) binding.tvTextUrok11.append("$simbol1 ")
                else binding.tvTextUrok11.append("$simbol2 ")
                flag = !flag
            }
            if (n % 2 == 0) flag = !flag
            binding.tvTextUrok11.append("\n")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}