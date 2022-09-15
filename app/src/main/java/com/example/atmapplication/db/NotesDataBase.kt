package com.example.atmapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.atmapplication.dto.Notes

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDataBase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {
        @Volatile
        private var instance: NotesDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NotesDataBase::class.java,
                "notes_table"
            ).build()
    }
}