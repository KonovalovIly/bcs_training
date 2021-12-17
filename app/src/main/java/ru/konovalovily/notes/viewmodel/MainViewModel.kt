package ru.konovalovily.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.model.NoteRepository

class MainViewModel(private val repository: NoteRepository) : ViewModel() {

    val noteData: LiveData<List<NoteModel>> = repository.notesList()

    fun deleteNote(id: Long) {
        repository.deleteNote(id)
    }

}