package com.example.uroki.urok7

import android.app.LauncherActivity.ListItem
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uroki.R

//https://www.youtube.com/watch?v=hcYVBg--2s8

class MyAdapterUrok7(listMainUrok7: ArrayList<ListItemUrok7>, context: Context): RecyclerView.Adapter<MyAdapterUrok7.MyHolder>() {

    var listArray = listMainUrok7
    val context = context

    class MyHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.id_title_urok7)
        val textTime = itemView.findViewById<TextView>(R.id.textViewTime_urok7)
        val context = context

        fun setData(item: ListItemUrok7){

            tvTitle.text = item.title
            textTime.text = item.time

            itemView.setOnClickListener{
                val intent = Intent(context, EditActivityUrok7::class.java).apply {
                    putExtra(MyIntentConstansUrok7.I_TITLE_KEY_UROK7, item.title)
                    putExtra(MyIntentConstansUrok7.I_DISC_KEY_UROK7, item.desc)
                    putExtra(MyIntentConstansUrok7.I_URI_KEY_UROK7, item.uri)
                    putExtra(MyIntentConstansUrok7.I_ID_KEY_UROK7, item.id)

                }

                context.startActivity(intent)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MyHolder(inflater.inflate(R.layout.rc_view_urok7, parent, false), context)
    }

    override fun getItemCount(): Int {
        return listArray.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray[position])
    }

    fun updateAdapter(listItems: List<ListItemUrok7>){

        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()

    }

    fun removeItem(position: Int, dbManager: MyDbManager){

        dbManager.removeItemFromDb(listArray[position].id.toString())
        listArray.removeAt(position)
        notifyItemRangeChanged(0, listArray.size)
        notifyItemRemoved(position)

    }
}