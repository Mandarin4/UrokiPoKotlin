package com.example.uroki

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Urok6 : AppCompatActivity() {
    var  counter = 0

    var textView: TextView? = null
    var button: Button? = null

    var pref: SharedPreferences? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urok6)

        textView = findViewById(R.id.textView_urok6)
        button = findViewById(R.id.button1_urok6)

        pref = getSharedPreferences("TABLE1", Context.MODE_PRIVATE)

        counter = pref?.getInt("counter", 0)!!
        textView?.text = counter.toString()

    }

    fun onClickAdd(view: View) {
        counter++
        textView?.text = counter.toString()
    }

    fun saveData(res: Int){
        val editor = pref?.edit()
        editor?.putInt("counter", res)
        editor?.apply()
    }


    override fun onDestroy() {
        super.onDestroy()
        saveData(counter)
    }

    fun deleteAll(){
        val editor = pref?.edit()
        editor?.clear()
        editor?.apply()
        counter = 0
    }

    fun deleteItem(deleteItem:String){
        val editor = pref?.edit()
        editor?.remove(deleteItem)
        editor?.apply()
    }

    fun onClickDeleteItem(view: View) {
        deleteItem("counter")
    }

    fun onClickClear(view: View) {
        deleteAll()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}