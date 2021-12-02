package ru.konovalovily.notes.presenter


import android.annotation.SuppressLint
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.model.NoteListModel
import ru.konovalovily.notes.view.EditNotesView
import java.text.SimpleDateFormat
import java.util.*

class EditPresenter(private val view: EditNotesView) : EditNotesPresenter {

    @SuppressLint("SimpleDateFormat")
    override fun saveNote(title: String, text: String) {

        view.showMessage(R.string.save_message, title)
        val sdf = SimpleDateFormat("dd.M.yyyy")
        val currentDate = sdf.format(Date())
        NoteListModel.addNoteToList(NoteModel(title, text, currentDate))
        view.createActivityIntent()
    }


    override fun onShareButton(title: String, text: String) {

        view.createShareIntent(title, text)
    }

}