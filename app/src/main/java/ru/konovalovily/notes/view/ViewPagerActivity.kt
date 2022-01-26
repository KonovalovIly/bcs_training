package ru.konovalovily.notes.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.callback.SaveActionModeCallback
import ru.konovalovily.notes.contracts.EditingNote
import ru.konovalovily.notes.contracts.IconDisplay
import ru.konovalovily.notes.databinding.ActivityViewPagerBinding
import ru.konovalovily.notes.viewmodel.MainViewModel

class ViewPagerActivity : AppCompatActivity(), EditingNote, IconDisplay {

    private lateinit var binding: ActivityViewPagerBinding

    private val viewModel by viewModel<MainViewModel>()

    private var adapter: ViewPagerAdapter = ViewPagerAdapter(emptyList(), this)
    override var currentFragment: NoteDescriptionFragment? = null
    private var supportActionMode: ActionMode? = null

    private var startState = true

    private var noteItem: NoteModel? = null
    private var positionFragmentItemId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
        noteItem = intent.getParcelableExtra(EXTRA_NOTE_ITEM)
    }


    private fun initViewPager() {
        binding.viewPager2.adapter = adapter

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.noteData.observe(
            this, {
                updateData(it)
                if (startState) {
                    findPositionFragment()
                    binding.viewPager2.setCurrentItem(positionFragmentItemId, false)
                    startState = false
                }
            }
        )

    }

    private fun findPositionFragment() {
        viewModel.setNote(noteItem)
        viewModel.positionNoteItem.observe(this) {
            positionFragmentItemId = it
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(noteList: List<NoteModel>) {
        adapter.updateData(noteList)
        adapter.notifyDataSetChanged()
    }

    override fun displaySaveActionMode() {
        supportActionMode = startSupportActionMode(SaveActionModeCallback(this))
    }

    override fun hideSaveActionMode() {
        supportActionMode?.finish()
    }

    companion object {

        private const val EXTRA_NOTE_ITEM = "extra_note_item"

        fun newIntentEditItem(context: Context, noteItem: NoteModel): Intent =
            Intent(context, ViewPagerActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ITEM, noteItem)
            }
    }

}