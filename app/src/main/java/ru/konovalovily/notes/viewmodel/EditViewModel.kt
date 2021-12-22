package ru.konovalovily.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.model.NetworkRepository
import ru.konovalovily.notes.model.NoteRepository

class EditViewModel(private val repository: NoteRepository) : ViewModel() {

    private val networkRepository = NetworkRepository()

    val note: MutableLiveData<NoteModel> = networkRepository.note

    fun saveNote(title: String, text: String, currentDate: String) {
        repository.saveNote(title = title, text = text, currentDate = currentDate)
    }

    fun getNote() {
        networkRepository.noteFromFirebase()
    }

}