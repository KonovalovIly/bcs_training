package ru.konovalovily.notes.presenter


import ru.konovalovily.notes.R
import ru.konovalovily.notes.view.EditActivity

class EditPresenter(private val view: EditActivity) : EditNotesPresenter {

    override fun saveNote(title: String, text: String) {

        view.showMessage(view.baseContext.getString(R.string.save_message, title))
        view.createActivityIntent(title, text)

    }

    override fun extractText(text: String): String {

        var textString = text
        if (text.isEmpty()) textString = view.baseContext.getString(R.string.empty_note)

        return textString
    }

    override fun onShareButton(title: String, text: String) {
        view.createShareIntent(title, text)
    }

    override fun extractTitle(title: String): String {

        var titleString = title
        if (title.isEmpty()) titleString = view.baseContext.getString(R.string.empty_note_title)

        return titleString
    }

}