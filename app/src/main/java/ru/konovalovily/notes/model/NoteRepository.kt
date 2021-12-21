package ru.konovalovily.notes.model

import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import java.util.*

class NoteRepository(private val noteDatabase: NoteDatabase) {

    fun saveNote(title: String, text: String, currentDate: String) {
        noteDatabase.noteDao()
            .insert(NoteModel(title = title, text = text, data = currentDate))
    }

    fun notesList(): LiveData<List<NoteModel>> = noteDatabase.noteDao().getNotes()

    fun deleteNote(id: Long) {
        noteDatabase.noteDao().deleteNote(id)
    }

    fun updateNote(note: NoteModel) {
        noteDatabase.noteDao().updateNote(note)
    }

}