package ru.konovalovily.notes.contracts

import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.view.MyItemRecyclerViewAdapter

interface MainContract {

    interface View {
        fun updateData()
    }

    interface Presenter {
        fun noteData(): List<NoteModel>
        fun deleteNote(id: Int, view: View, adapter: MyItemRecyclerViewAdapter)
    }
}