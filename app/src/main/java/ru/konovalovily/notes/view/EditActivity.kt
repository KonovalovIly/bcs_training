package ru.konovalovily.notes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.appbar.MaterialToolbar
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.EditContract
import ru.konovalovily.notes.databinding.ActivityEditBinding
import ru.konovalovily.notes.model.NoteDatabase
import ru.konovalovily.notes.presenter.EditPresenter

class EditActivity : AppCompatActivity(), EditContract.View {

    private lateinit var binding: ActivityEditBinding

    private lateinit var title: AppCompatEditText
    private lateinit var text: AppCompatEditText
    private lateinit var toolbar: MaterialToolbar

    private lateinit var presenter: EditContract.Presenter

    private lateinit var emptyNote: String
    private lateinit var emptyNoteTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initField()
        initFun()
    }

    override fun showMessage(message: Int, title: String) {

        Toast.makeText(this, getString(message, title), Toast.LENGTH_SHORT).show()
    }

    override fun openShareIntent(title: String, text: String) {

        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "$title \n $text")
        })
    }

    override fun showDialog(title: String, text: String) {
        AlertDialog.Builder(this)
            .setMessage(R.string.dialog_message)
            .setPositiveButton(
                R.string.positive
            ) { _, _ ->
                presenter.saveNote(title, text)
            }.setNegativeButton(R.string.negative) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(true)
            .create().show()
    }

    private fun initField() {

        title = binding.etTitle
        text = binding.etText
        toolbar = binding.toolbar
        presenter = EditPresenter(this, NoteDatabase.getInstance(this))

        emptyNote = getString(R.string.empty_note)
        emptyNoteTitle = getString(R.string.empty_note_title)
    }

    private fun initFun() {

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        toolbar.setOnMenuItemClickListener {

            val titleString =
                if (title.text.toString().isEmpty()) emptyNoteTitle else title.text.toString()
            val textString =
                if (text.text.toString().isEmpty()) emptyNote else text.text.toString()

            when (it.itemId) {
                R.id.share -> {
                    presenter.onShareButton(titleString, textString)
                    true
                }
                R.id.save -> {
                    showDialog(titleString, textString)
                    true
                }
                else -> false
            }
        }
    }
}