package ru.konovalovily.notes.viewmodel

import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.model.NoteRepository

class UpdateViewModel(private val repository: NoteRepository) : ViewModel() {

    fun updateNote(note: NoteModel) {
        repository.updateNote(note)
    }
}