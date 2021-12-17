package ru.konovalovily.notes.viewmodel

import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.model.NoteRepository

class EditViewModel(private val repository: NoteRepository) : ViewModel() {

    fun saveNote(title: String, text: String) {
        repository.saveNote(title = title, text = text)
    }

}