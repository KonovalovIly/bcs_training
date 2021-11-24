package ru.konovalovily.notes.presenter

import ru.konovalovily.notes.view.EditNotesView

class EditPresenter(private val view: EditNotesView) : EditNotesPresenter {

    override fun saveNote(title: String, text: String) {

        view.showMessage("Вы сохранили заметку $title")

    }

    override fun extractText(text: String): String {

        var textString = text
        if (text.isEmpty()) textString = "Пустая заметка"

        return textString
    }

    override fun extractTitle(title: String): String {

        var titleString = title
        if (title.isEmpty()) titleString = "Без названия"

        return titleString
    }

}