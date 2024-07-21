package com.example.uroki.urok3

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uroki.R
import com.google.android.material.navigation.NavigationView

// видеоурок 9 закончил https://www.youtube.com/watch?v=-vxTwjgW3MY&list=PLmjT2NFTgg1clSDgx1YYOuVyZuCXVjfuR&index=9

class Urok3 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var nav_view: NavigationView? = null
    var rcView: RecyclerView? = null

    var adapter: MyAdapterUrok3? = null
    var navigationId: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urok3)

        navigationId = findViewById(R.id.navigationId)

        nav_view = findViewById(R.id.nav_view)
        nav_view?.setNavigationItemSelectedListener (this)

        var list = ArrayList<ListItem_Urok3>()

        list.addAll(fillArras(resources.getStringArray(R.array.fish),resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
        rcView = findViewById(R.id.rcVew)
        rcView?.hasFixedSize()
        rcView?.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapterUrok3(list, this)
        rcView?.adapter = adapter


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.text_resurse_menu_1 ->{
                adapter?.apdateAdapter(fillArras(resources.getStringArray(R.array.fish),resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
                Toast.makeText(this, "id fish",Toast.LENGTH_SHORT).show()
            }
            R.id.text_resurse_menu_2 -> {
                adapter?.apdateAdapter(fillArras(resources.getStringArray(R.array.na),resources.getStringArray(R.array.na_content),getImageId(R.array.na_image_array)))
                Toast.makeText(this, "id najivka",Toast.LENGTH_SHORT).show()
            }
            R.id.text_resurse_menu_3 -> {
                adapter?.apdateAdapter(fillArras(resources.getStringArray(R.array.sna),resources.getStringArray(R.array.sna_content),getImageId(R.array.sna_image_array)))
                Toast.makeText(this, "id snasti",Toast.LENGTH_SHORT).show()
            }
            R.id.text_resurse_menu_4 -> {
                adapter?.apdateAdapter(fillArras(resources.getStringArray(R.array.istr),resources.getStringArray(R.array.istr_content),getImageId(R.array.istr_image_array)))
                Toast.makeText(this, "id istoriya",Toast.LENGTH_SHORT).show()
            }
            R.id.text_resurse_menu_11 -> Toast.makeText(this, "id fish__1",Toast.LENGTH_SHORT).show()
            R.id.text_resurse_menu_21 -> Toast.makeText(this, "id najivka__1",Toast.LENGTH_SHORT).show()
            R.id.text_resurse_menu_31 -> Toast.makeText(this, "id snasti__1",Toast.LENGTH_SHORT).show()
            R.id.text_resurse_menu_41 -> Toast.makeText(this, "id istoriya__1",Toast.LENGTH_SHORT).show()
        }

        navigationId?.closeDrawer(GravityCompat.START)

        return true
    }

    fun fillArras(titleArray:Array<String>, contentArray: Array<String>, imagaArray: IntArray):List<ListItem_Urok3>{

        var listItemArray = ArrayList<ListItem_Urok3>()
        for (i in 0..titleArray.size-1){
            var listItem = ListItem_Urok3(imagaArray[i],titleArray[i], contentArray[i])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId:Int): IntArray {
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices){
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Пока, пока!", Toast.LENGTH_SHORT).show()
        finish()
    }
}