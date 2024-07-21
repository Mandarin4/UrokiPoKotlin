package com.example.uroki.urok7

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.uroki.urok7.db.MyDbNameClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyDbManager(val context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDbHelper.writableDatabase
    }

    suspend fun insertToDb(title: String, content: String, uri: String, time: String) = withContext(Dispatchers.IO){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMAGE_URL, uri)
            put(MyDbNameClass.COLUMN_NAME_DATATIME, time)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }


    suspend fun readDbData(searchText: String): ArrayList<ListItemUrok7> = withContext(Dispatchers.IO){
        val dataList = ArrayList<ListItemUrok7>()
        val selection = "${MyDbNameClass.COLUMN_NAME_TITLE} like ?"
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, selection,
            arrayOf("%$searchText%"), null, null, null)


        while (cursor?.moveToNext()!!){
            val dataId = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val dataTitle = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_TITLE))
            val dataContent = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_CONTENT))
            val dataUri = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_IMAGE_URL))
            val dataTime = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_DATATIME))
            val item = ListItemUrok7()
            item.id = dataId
            item.title = dataTitle
            item.desc = dataContent
            item.uri = dataUri
            item.time = dataTime
            dataList.add(item)
        }
        cursor.close()
        return@withContext dataList
    }

    fun removeItemFromDb(id: String){
        val selection = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selection, null)
    }

    suspend fun updateItem(title: String, content: String, uri: String, id: Int, time: String) = withContext(Dispatchers.IO){
        val selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDbNameClass.COLUMN_NAME_IMAGE_URL, uri)
            put(MyDbNameClass.COLUMN_NAME_DATATIME, time)
        }
        db?.update(MyDbNameClass.TABLE_NAME, values, selection, null)
    }

    fun closeDb(){
        myDbHelper.close()
    }

}