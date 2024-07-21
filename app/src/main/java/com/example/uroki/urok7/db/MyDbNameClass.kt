package com.example.uroki.urok7.db

import android.provider.BaseColumns

object MyDbNameClass: BaseColumns {
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    const val COLUMN_NAME_IMAGE_URL = "uri"
    const val COLUMN_NAME_DATATIME = "time"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyDbUrok7.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "$COLUMN_NAME_TITLE TEXT, " +
            "$COLUMN_NAME_CONTENT TEXT, " +
            "$COLUMN_NAME_IMAGE_URL TEXT, " +
            "$COLUMN_NAME_DATATIME TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}