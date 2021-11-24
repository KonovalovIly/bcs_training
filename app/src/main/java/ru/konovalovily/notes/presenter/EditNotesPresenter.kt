package ru.konovalovily.notes.presenter

interface EditNotesPresenter {
    fun saveNote(title: String, text: String)
    fun extractTitle(title: String): String
    fun extractText(text: String): String
}