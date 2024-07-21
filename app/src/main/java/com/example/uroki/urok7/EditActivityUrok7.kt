package com.example.uroki.urok7

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.transition.Visibility
import com.example.uroki.databinding.ActivityEditUrok7Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditActivityUrok7 : AppCompatActivity() {

    private lateinit var binding: ActivityEditUrok7Binding

    val myDbManager = MyDbManager(this)

    var id = 0
    var isEditState = false
    val imageRequestCode = 10
    var tempImageUri = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUrok7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        getMyIntents()
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()

    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imageRequestCode){

            binding.imMainImageUrok7.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
            contentResolver.takePersistableUriPermission(data?.data!!,
                Intent.FLAG_GRANT_READ_URI_PERMISSION)

        }
    }

    fun onClickAddImage(view: View) {
        binding.mainImageLayout.visibility = View.VISIBLE
        binding.fdAddImage.visibility = View.GONE
    }

    fun deleteImage(view: View) {
        binding.mainImageLayout.visibility = View.GONE
        binding.fdAddImage.visibility = View.VISIBLE
        tempImageUri = "empty"
    }

    fun noClickChooseImage(view: View) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, imageRequestCode)

    }

    fun onClickSave(view: View) {
        val myTitle = binding.edTitle1Urok7.text.toString()
        val myDisc = binding.edTitle2Urok7.text.toString()

        if(myTitle != "" && myDisc != ""){
            CoroutineScope(Dispatchers.Main).launch {
                if (isEditState){
                    myDbManager.updateItem(myTitle, myDisc, tempImageUri, id, getCurrentTime())
                }else{
                    myDbManager.insertToDb(myTitle, myDisc, tempImageUri, getCurrentTime())
                }

                finish()
            }
        }
    }

    fun getMyIntents(){
        binding.readktUrok7.visibility = View.GONE
        val i = intent
        if (i != null){

            Log.d("MyLog", "Data recived: " + i.getStringExtra(MyIntentConstansUrok7.I_TITLE_KEY_UROK7))

            if (i.getStringExtra(MyIntentConstansUrok7.I_TITLE_KEY_UROK7) != null){
                binding.fdAddImage.visibility = View.GONE
                binding.edTitle1Urok7.setText(i.getStringExtra(MyIntentConstansUrok7.I_TITLE_KEY_UROK7))
                binding.edTitle2Urok7.setText(i.getStringExtra(MyIntentConstansUrok7.I_DISC_KEY_UROK7))
                id = i.getIntExtra(MyIntentConstansUrok7.I_ID_KEY_UROK7, 0)
                isEditState = true
                binding.edTitle1Urok7.isEnabled = false
                binding.edTitle2Urok7.isEnabled = false
                binding.readktUrok7.visibility = View.VISIBLE
                if(i.getStringExtra(MyIntentConstansUrok7.I_URI_KEY_UROK7) != "empty"){

                    binding.mainImageLayout.visibility = View.VISIBLE
                    tempImageUri = i.getStringExtra(MyIntentConstansUrok7.I_URI_KEY_UROK7)!!
                    binding.imMainImageUrok7.setImageURI(Uri.parse(tempImageUri))
                    Log.d("MyLog", "Data recived: " + i.getStringExtra(MyIntentConstansUrok7.I_URI_KEY_UROK7))
                    binding.imageButDelete.visibility = View.GONE
                    binding.imageButtonEdit.visibility = View.GONE
                }
            }
        }
    }

    fun onEditEnable(view: View) {
        binding.edTitle1Urok7.isEnabled = true
        binding.edTitle2Urok7.isEnabled = true
        binding.readktUrok7.visibility = View.GONE
        binding.fdAddImage.visibility = View.VISIBLE
        if (tempImageUri != "empty"){
            binding.imageButtonEdit.visibility = View.VISIBLE
            binding.imageButDelete.visibility = View.VISIBLE
        }
    }

    private fun getCurrentTime():String{
        val time = Calendar.getInstance().time
        val formater = SimpleDateFormat("dd-MM-yy kk:mm", Locale.getDefault())
        val fTime = formater.format(time)
        return fTime.toString()
    }

}