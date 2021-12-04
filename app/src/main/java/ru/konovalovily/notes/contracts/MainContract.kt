package ru.konovalovily.notes.contracts

import ru.konovalovily.notes.NoteModel

interface MainContract {

    interface Presenter{
        fun noteData(): ArrayList<NoteModel>
    }
}