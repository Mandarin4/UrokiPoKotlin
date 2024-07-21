package com.example.uroki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uroki.databinding.ActivityUrok8Binding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//https://www.youtube.com/watch?v=KiJy5Oi4rRo&list=PLEGrY4uRTu5ls7Mq7h6RcdKGFdQVqy0KZ
//https://www.youtube.com/watch?v=cVKYl-wWCUA


class Urok8 : AppCompatActivity() {
    private lateinit var binding: ActivityUrok8Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUrok8Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Write a message to the database
        val database = Firebase.database

        val myRef = database.getReference("message")
        binding.bSendUrok8.setOnClickListener {
            myRef.setValue(binding.editTextUrok8.text.toString())
        }

        onChangeListener(myRef)

        Thread{
            //Toast.makeText(this, "Второстепенный поток", Toast.LENGTH_SHORT).show()
            runOnUiThread{
                Toast.makeText(this, "Возврат в основной поток1", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    rcViewUrok8.append("Alexey : ${snapshot.value.toString()}")
                    rcViewUrok8.append("\n")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}