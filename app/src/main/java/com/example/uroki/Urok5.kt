package com.example.uroki

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

//https://www.youtube.com/watch?v=kloLNIkrBI4&list=PLmjT2NFTgg1clSDgx1YYOuVyZuCXVjfuR&index=15

class Urok5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urok5)

        createSimpleMultiChoiseDialog()
        createSimpleDialog()
    }

    private fun createSimpleDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Это алерт диалог")
        builder.setMessage("это долбанный алерт")
        builder.setNeutralButton("Info", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        builder.setNegativeButton("No"){dialog,i ->

        }
        builder.setPositiveButton("Yes"){dialog,i ->

        }
        builder.show()
    }

    private fun createSimpleMultiChoiseDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Это супер алерт диалог")
        builder.setMultiChoiceItems(R.array.multi_item_alert, null){dialog, wich, choise ->
            Log.d("MyLog", "My Choise is: " + wich + " / is : " + choise)

        }
        builder.setNeutralButton("Info", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        builder.setNegativeButton("No"){dialog,i ->

        }
        builder.setPositiveButton("Yes"){dialog,i ->

        }
        builder.show()
    }



    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}