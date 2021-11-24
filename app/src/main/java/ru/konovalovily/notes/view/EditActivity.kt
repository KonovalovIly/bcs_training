package ru.konovalovily.notes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.appbar.MaterialToolbar
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.R
import ru.konovalovily.notes.databinding.ActivityEditBinding
import ru.konovalovily.notes.presenter.EditNotesPresenter
import ru.konovalovily.notes.presenter.EditPresenter

class EditActivity : AppCompatActivity(), EditNotesView {

    private lateinit var binding: ActivityEditBinding

    private lateinit var title: AppCompatEditText
    private lateinit var text: AppCompatEditText
    private lateinit var toolbar: MaterialToolbar

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
        title = binding.etTitle
        text = binding.etText
        toolbar = binding.toolbar
        presenter = EditPresenter(this)
    }

    private fun initFun() {

        toolbar.setOnMenuItemClickListener {

            val titleString = presenter.extractTitle(title.text.toString())
            val textString = presenter.extractText(text.text.toString())

            when (it.itemId) {
                R.id.share -> {
                    startActivity(Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "$titleString \n $textString")
                    })
                    true
                }
                R.id.save -> {
                    presenter.saveNote(titleString, textString)
                    startActivity(
                        Intent(this@EditActivity, MainActivity::class.java)
                            .putExtra(Constant.TITLE_TAG, titleString)
                            .putExtra(Constant.TEXT_TAG, textString)
                    )
                    true
                }
                else -> false
            }
        }
    }
}