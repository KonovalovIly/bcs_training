package ru.konovalovily.notes.presenter

import androidx.fragment.app.Fragment
import ru.konovalovily.notes.NoteModel

interface MainNotePresenter {

    fun getNoteData(): ArrayList<NoteModel>
    fun createFragment(resId: Int, classFragment: Fragment)
    fun addDescriptionFragment(resId: Int, classFragment: Fragment)
}