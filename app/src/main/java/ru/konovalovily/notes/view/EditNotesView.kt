package ru.konovalovily.notes.view

interface EditNotesView {
    fun showMessage(message: String)
    fun createShareIntent(title: String, text: String)
    fun createActivityIntent(title: String, text: String)
}