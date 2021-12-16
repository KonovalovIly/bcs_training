package ru.konovalovily.notes.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.model.NoteRepository

class EditViewModel(private val repository: NoteRepository) : ViewModel() {

    @SuppressLint("SimpleDateFormat")
    fun saveNote(title: String, text: String) {

        val titleString =
            if (title.isEmpty()) Constant.UNTITLED else title
        val textString =
            if (text.isEmpty()) Constant.EMPTY_NOTE else text

        repository.saveNote(title = titleString, text = textString)
    }

}