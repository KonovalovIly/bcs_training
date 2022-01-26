package ru.konovalovily.notes.contracts

interface EditContract {

    interface View {
        fun showMessage(message: Int, title: String)
        fun openShareIntent(title: String, text: String)
        fun showDialog(title: String, text: String)
    }

    interface Presenter {
        fun saveNote(title: String, text: String)
        fun onShareButton(title: String, text: String)
    }
}