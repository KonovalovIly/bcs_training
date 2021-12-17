package ru.konovalovily.notes.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.model.NoteRepository

class EditViewModel(private val repository: NoteRepository) : ViewModel() {

    @SuppressLint("SimpleDateFormat")
    fun saveNote(title: String, text: String) {

        repository.saveNote(title = title, text = text)
    }

}