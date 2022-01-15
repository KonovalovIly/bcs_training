package ru.konovalovily.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.model.NoteRepository

class MainViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _currentNoteList: MutableLiveData<List<NoteModel>> = MutableLiveData()
    val currentNoteList: LiveData<List<NoteModel>>
        get() = _currentNoteList

    private val _currentFilteredNoteList: MutableLiveData<List<NoteModel>> = MutableLiveData()
    val currentFilteredNoteList: LiveData<List<NoteModel>>
        get() = _currentFilteredNoteList

    val noteData: LiveData<List<NoteModel>> = repository.notesList()

    private val _positionNoteItem: MutableLiveData<Int> = MutableLiveData()
    val positionNoteItem: LiveData<Int>
        get() = _positionNoteItem

    fun deleteNote(id: Long) {
        repository.deleteNote(id)
    }

    fun setNote(noteItem: NoteModel?) {
        _positionNoteItem.value = noteData.value?.indexOf(noteItem)
    }


    fun filterNotes(query: String?) {
        _currentFilteredNoteList.value = when (query) {
            null -> _currentNoteList.value
            else -> _currentNoteList.value?.filter {
                it.text.contains(query, ignoreCase = true) ||
                        it.title.contains(query, ignoreCase = true)
            }
        }
    }

    fun updateCurrentList(list: List<NoteModel>) {
        _currentNoteList.value = list
    }
}