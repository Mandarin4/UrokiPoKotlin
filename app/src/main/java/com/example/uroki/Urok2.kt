package com.example.uroki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.ListView

class Urok2 : AppCompatActivity() {

    var urok2_listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urok2)

        urok2_listView = findViewById(R.id.urok2_listView)

        var nameList = ArrayList<String>()
        nameList.add("Серега")
        nameList.add("Леха")
        nameList.add("Степан")
        nameList.add("Егро")
        nameList.add("ПУшин")
        nameList.add("Кенфор")
        nameList.add("ЛОгфорт")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList)
        urok2_listView?.adapter = adapter
        urok2_listView?.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Ваша позиция: $position \n" + "Ваше имя: ${nameList.get(position)}" , Toast.LENGTH_SHORT).show()
        }


    }



    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}