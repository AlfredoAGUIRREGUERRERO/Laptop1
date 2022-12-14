package com.example.laptop.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
      p0?.execSQL("CREATE TABLE $TABLE_LAPTOPS(id INTEGER PRIMARY KEY AUTOINCREMENT, marca TEXT NOT NULL, tipo TEXT NOT NULL, developer TEXT NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
      p0?.execSQL("DROP TABLE $TABLE_LAPTOPS")
        onCreate(p0)
    }
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "laptops.db"
        public const val TABLE_LAPTOPS ="laptops"
    }
}