package ru.konovalovily.notes.model

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import ru.konovalovily.notes.NoteModel
import java.util.*

class NoteRepository(private val context: Context) {

    private val noteDatabase: NoteDatabase = NoteDatabase.getInstance(context)

    fun saveNote(title: String, text: String) {

        val currentDate = DateFormat.getDateFormat(context).format(Date())
        noteDatabase.noteDao().insert(NoteModel(title = title, text = text, data = currentDate))
    }

    fun notesList(): LiveData<List<NoteModel>> = noteDatabase.noteDao().getNotes()

    fun deleteNote(id: Long) {
        noteDatabase.noteDao().deleteNote(id)
    }

}