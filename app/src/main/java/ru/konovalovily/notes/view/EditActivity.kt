package ru.konovalovily.notes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.Saving
import ru.konovalovily.notes.databinding.ActivityEditBinding
import ru.konovalovily.notes.viewmodel.EditViewModel

class EditActivity : AppCompatActivity(), Saving {

    private lateinit var binding: ActivityEditBinding

    private lateinit var title: AppCompatEditText
    private lateinit var text: AppCompatEditText
    private lateinit var toolbar: MaterialToolbar

    private val viewModel by viewModel<EditViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initField()
        initFun()
    }

    private fun openShareIntent(title: String, text: String) {

        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constant.SHARE_TYPE
            putExtra(Intent.EXTRA_TEXT, "$title \n $text")
        })
    }

    private fun initField() {

        title = binding.etTitle
        text = binding.etText
        toolbar = binding.toolbar
    }

    private fun initFun() {

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.share -> {
                    openShareIntent(title.text.toString(), text.text.toString())
                    true
                }
                R.id.save -> {
                    DialogSaveNoteFragment().show(supportFragmentManager, Constant.TITLE_TAG)
                    true
                }
                else -> false
            }
        }
    }

    override fun saveNote() {
        viewModel.saveNote(title.text.toString(), text.text.toString())
    }
}