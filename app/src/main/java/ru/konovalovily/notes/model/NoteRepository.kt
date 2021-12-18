package ru.konovalovily.notes.model

import android.content.Context
import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import java.util.*

class NoteRepository(private val context: Context) {

    private val noteDatabase: NoteDatabase = NoteDatabase.getInstance(context)

    fun saveNote(title: String, text: String) {

        val titleString =
            if (title.isEmpty()) context.getString(R.string.untitled) else title
        val textString =
            if (text.isEmpty()) context.getString(R.string.empty_note) else text

        val currentDate = DateFormat.getDateFormat(context).format(Date())
        noteDatabase.noteDao()
            .insert(NoteModel(title = titleString, text = textString, data = currentDate))
    }

    fun notesList(): LiveData<List<NoteModel>> = noteDatabase.noteDao().getNotes()

    fun deleteNote(id: Long) {
        noteDatabase.noteDao().deleteNote(id)
    }

    fun updateNote(note: NoteModel) {
        noteDatabase.noteDao().updateNote(note)
    }

}