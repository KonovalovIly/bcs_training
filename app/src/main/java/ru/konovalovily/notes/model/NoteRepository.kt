package ru.konovalovily.notes.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.konovalovily.notes.NoteModel

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

    fun getNotesForBackup(): Flow<List<NoteModel>> =
        noteDatabase.noteDao().getAllNotesForBackup()

}