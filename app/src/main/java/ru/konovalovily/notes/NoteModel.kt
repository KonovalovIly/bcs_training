package ru.konovalovily.notes

import java.io.Serializable

data class NoteModel(
    var title: String,
    var text: String,
    var data: String
) : Serializable
