package ru.konovalovily.notes.presenter

interface EditNotesPresenter {

    fun saveNote(title: String, text: String)
    fun onShareButton(title: String, text: String)
}