package com.pcx.quizapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    NAME_COL + " TEXT NOT NULL, " +
                    FLAG_COL + " TEXT NOT NULL, " +
                    CAPITAL_COL + " TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        private const val DB_NAME = "quiz.sqlite"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "countries"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val FLAG_COL = "flag"
        private const val CAPITAL_COL = "capital"
    }
}