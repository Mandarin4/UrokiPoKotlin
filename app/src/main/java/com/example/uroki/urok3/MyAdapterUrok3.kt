package com.example.uroki.urok3

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.uroki.R

class MyAdapterUrok3 (listArray: ArrayList<ListItem_Urok3>, context: Context):RecyclerView.Adapter<MyAdapterUrok3.ViewHolder>() {

    var listArrayR = listArray
    var contextR = context

    class ViewHolder (view: View):RecyclerView.ViewHolder(view) {

        val tvTitle = view.findViewById<TextView>(R.id.textView3_item_urok3)
        val tvContext = view.findViewById<TextView>(R.id.textView4_item_urok3)
        val im = view.findViewById<ImageView>(R.id.imageView_item_urok3)
        val itemView_urok_3 = view.findViewById<ConstraintLayout>(R.id.itemView_urok_3)

        fun bind(listItem: ListItem_Urok3, context: Context){

            tvTitle.text = listItem.titleText
            //val textcon: String = listItem.contentText.substring(0,50)
           // tvContext.text = textcon
            tvContext.text = listItem.contentText
            im.setImageResource(listItem.imageId)

            itemView_urok_3.setOnClickListener {
                Toast.makeText(context, "Pressed : ${tvTitle.text}", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, ContentActivity::class.java).apply {
                    putExtra("title",tvTitle.text.toString())
                    //putExtra("content",listItem.contentText.toString())
                    putExtra("content",listItem.contentText)
                    putExtra("image",listItem.imageId)
                }
                context.startActivity(intent)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)

        return ViewHolder(inflater.inflate(R.layout.item_layout_urok_3, parent, false))
    }

    override fun getItemCount(): Int {
        return listArrayR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var listItem = listArrayR.get(position)
        holder.bind(listItem, contextR)

    }

    fun apdateAdapter(listArray: List<ListItem_Urok3>){

        listArrayR.clear()
        listArrayR.addAll(listArray)
        notifyDataSetChanged()

    }

}