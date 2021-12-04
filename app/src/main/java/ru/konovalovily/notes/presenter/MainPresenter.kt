package ru.konovalovily.notes.presenter

import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.contracts.MainContract
import ru.konovalovily.notes.model.NoteListModel

class MainPresenter : MainContract.Presenter {

    override fun noteData(): ArrayList<NoteModel> = NoteListModel.notes

}