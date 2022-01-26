package ru.konovalovily.notes.model

import ru.konovalovily.notes.NoteModel

object NoteListModel {

    val notes = ArrayList<NoteModel>().apply {
        add(NoteModel("First Note", "First Text", "16.08.2020"))
        add(NoteModel("Second Note", "Second Text", "16.08.2021"))
        add(
            NoteModel(
                "Third Note",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                        " ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                        " reprehenderit in voluptate velit esse cillum dolore eu" +
                        " fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                        " sunt in culpa qui officia deserunt mollit anim id est laborum." +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                        " ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                        " reprehenderit in voluptate velit esse cillum dolore eu" +
                        " fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident," +
                        " sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "16.08.2022"
            )
        )
    }


    fun addNoteToList(noteModel: NoteModel) {
        notes.add(noteModel)
    }
}