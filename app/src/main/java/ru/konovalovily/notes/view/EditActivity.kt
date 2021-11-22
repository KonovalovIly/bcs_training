package ru.konovalovily.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import ru.konovalovily.notes.databinding.ActivityEditBinding
import ru.konovalovily.notes.presenter.EditNotesPresenter
import ru.konovalovily.notes.presenter.EditPresenter

class EditActivity : AppCompatActivity(), EditNotesView {

    private lateinit var binding: ActivityEditBinding

    private lateinit var saveBtn: Button
    private lateinit var title: AppCompatEditText
    private lateinit var text: AppCompatEditText

    private lateinit var presenter: EditNotesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initField()
        initFun()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initField() {
        saveBtn = binding.saveButton
        title = binding.etTitle
        text = binding.etText
        presenter = EditPresenter(this)
    }

    private fun initFun() {

        saveBtn.setOnClickListener {
            presenter.saveNote(title.text.toString(), text.text.toString())
        }

    }

}