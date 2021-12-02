package ru.konovalovily.notes.presenter

import androidx.fragment.app.Fragment
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.model.NoteListModel
import ru.konovalovily.notes.view.MainNotesView

class MainPresenter(private val view: MainNotesView) : MainNotePresenter {

    override fun getNoteData(): ArrayList<NoteModel> = NoteListModel.getNoteList()

    override fun createFragment(resId: Int, classFragment: Fragment) {
        view.createFragment(resId, classFragment)
    }

    override fun addDescriptionFragment(resId: Int, classFragment: Fragment) {
        view.displayHomeButton()
        view.addDescriptionFragment(resId, classFragment)
    }
}