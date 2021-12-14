package ru.konovalovily.notes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.contracts.NoteDao


@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null
        private const val NAME_DATABASE = "database"

        fun getInstance(context: Context): NoteDatabase {
            return if (INSTANCE == null) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    NAME_DATABASE
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            } else INSTANCE!!
        }
    }
}