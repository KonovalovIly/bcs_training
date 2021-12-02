package ru.konovalovily.notes.view

interface EditNotesView {

    fun showMessage(message: Int, title: String)
    fun createShareIntent(title: String, text: String)
    fun createActivityIntent()
}