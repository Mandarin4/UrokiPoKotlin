package com.example.uroki.urok7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uroki.databinding.ActivityUrok7Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//https://developer.android.com/training/data-storage/sqlite#kotlin
//https://www.youtube.com/watch?v=2sVbaWrofy0&list=PLmjT2NFTgg1clSDgx1YYOuVyZuCXVjfuR&index=21
//https://www.youtube.com/watch?v=eEOSob7frz4&t=15s

class Urok7 : AppCompatActivity() {

    private lateinit var binding: ActivityUrok7Binding

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapterUrok7(ArrayList(), this)
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUrok7Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initSearchView()
    }

    fun onClickSaveNew(view: View) {
        val intent = Intent(this, EditActivityUrok7::class.java)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter("")
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun init(){
        binding.recyclerViewUrok7.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSmapMg()
        swapHelper.attachToRecyclerView(binding.recyclerViewUrok7)
        binding.recyclerViewUrok7.adapter = myAdapter
    }

    fun initSearchView(){
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("Mylog", "New text : $p0")
                fillAdapter(p0!!)
                return true
            }
        })
    }

    fun fillAdapter(text:String){
        job?.cancel()
        job = CoroutineScope(Dispatchers.Main).launch{//https://www.youtube.com/watch?v=qnQO3cnCShI

            val list = myDbManager.readDbData(text)
            myAdapter.updateAdapter(list)
            if(list.size>0){
                binding.tvNoElements.visibility = View.GONE
            }
            else binding.tvNoElements.visibility = View.VISIBLE
        }

    }

    private fun getSmapMg(): ItemTouchHelper{
        return ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}