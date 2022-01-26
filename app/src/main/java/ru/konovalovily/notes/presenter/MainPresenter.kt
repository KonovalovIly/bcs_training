package ru.konovalovily.notes.presenter

import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.contracts.MainContract
import ru.konovalovily.notes.model.NoteDatabase
import ru.konovalovily.notes.view.MyItemRecyclerViewAdapter

class MainPresenter(private val db: NoteDatabase) : MainContract.Presenter {

    override fun noteData(): List<NoteModel> = db.noteDao().getNotes()

    override fun deleteNote(id: Int, view: MainContract.View, adapter: MyItemRecyclerViewAdapter) {
        db.noteDao().deleteNote(adapter.getIdItem(id))
        view.updateData()
    }

}