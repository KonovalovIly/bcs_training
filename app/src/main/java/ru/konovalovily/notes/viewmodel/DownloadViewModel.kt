package ru.konovalovily.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.model.NetworkRepository
import ru.konovalovily.notes.model.NoteRepository

class DownloadViewModel(
    private val repository: NoteRepository,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val noteFromFirebase: MutableLiveData<NoteModel> = networkRepository.note

    fun getNote() {
        networkRepository.noteFromFirebase()
    }

    fun saveNote(noteModel: NoteModel) {
        repository.saveNote(
            title = noteModel.title,
            text = noteModel.text,
            currentDate = noteModel.data
        )
    }
}