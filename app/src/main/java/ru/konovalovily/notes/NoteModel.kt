package ru.konovalovily.notes

import java.io.Serializable

data class NoteModel(
    private var title: String,
    private var text: String,
    private var data: String
): Serializable {

    fun getTitle(): String = title
    fun getText(): String = text
    fun getData(): String = data
}
