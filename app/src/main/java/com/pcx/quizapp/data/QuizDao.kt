package com.pcx.quizapp.data

class QuizDao {

    fun get10RandomFlag(db: DatabaseHandler): ArrayList<Quiz> {
        val list = ArrayList<Quiz>()
        val cursor = db.writableDatabase.rawQuery(
            "SELECT * FROM countries ORDER BY RANDOM() LIMIT 10",
            null
        )
        while (cursor.moveToNext()) {
            val quiz = Quiz(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("name")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag")),
                cursor.getString(cursor.getColumnIndexOrThrow("capital"))
            )
            list.add(quiz)
        }
        cursor.close()
        return list
    }

    fun get3WrongAnswer(db: DatabaseHandler, id: Int): ArrayList<Quiz> {
        val list = ArrayList<Quiz>()
        val cursor = db.writableDatabase.rawQuery(
            "SELECT * FROM countries WHERE id != $id ORDER BY RANDOM() LIMIT 3",
            null
        )
        while (cursor.moveToNext()) {
            val quiz = Quiz(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("name")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag")),
                cursor.getString(cursor.getColumnIndexOrThrow("capital"))
            )
            list.add(quiz)
        }
        cursor.close()
        return list
    }
}