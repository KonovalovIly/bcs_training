package ru.konovalovily.notes.view

interface EditNotesView {
    fun showMessage(message: String)
    fun shareIntent(title: String, text: String)
    fun activityIntent(title: String, text: String)
}