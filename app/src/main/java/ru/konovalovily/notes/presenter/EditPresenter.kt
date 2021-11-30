package ru.konovalovily.notes.presenter


import ru.konovalovily.notes.R
import ru.konovalovily.notes.view.EditNotesView

class EditPresenter(private val view: EditNotesView) : EditNotesPresenter {

    override fun saveNote(title: String, text: String) {

        view.showMessage(R.string.save_message, title)
        view.createActivityIntent(title, text)
    }


    override fun onShareButton(title: String, text: String) {

        view.createShareIntent(title, text)
    }

}