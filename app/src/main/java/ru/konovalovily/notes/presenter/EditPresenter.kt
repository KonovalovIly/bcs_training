package ru.konovalovily.notes.presenter


import android.annotation.SuppressLint
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.EditContract
import ru.konovalovily.notes.model.NoteDatabase
import java.text.SimpleDateFormat
import java.util.*

class EditPresenter(
    private val view: EditContract.View,
    private val db: NoteDatabase
) : EditContract.Presenter {

    @SuppressLint("SimpleDateFormat")
    override fun saveNote(title: String, text: String) {

        view.showMessage(R.string.save_message, title)
        val dateFormat = SimpleDateFormat(Constant.DATE_FORMAT)
        val currentDate = dateFormat.format(Date())
        db.noteDao().insert(NoteModel(title = title, text = text, data = currentDate))
    }


    override fun onShareButton(title: String, text: String) {

        view.openShareIntent(title, text)
    }

}