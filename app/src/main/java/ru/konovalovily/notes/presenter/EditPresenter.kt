package ru.konovalovily.notes.presenter

import ru.konovalovily.notes.view.EditNotesView

class EditPresenter(private val view: EditNotesView) : EditNotesPresenter {

    override fun saveNote(title: String, text: String) {
        var titleToSave = title
        if (title.isEmpty()) titleToSave = "Без названия"

        view.showMessage("Вы сохранили заметку $titleToSave")

    }
}