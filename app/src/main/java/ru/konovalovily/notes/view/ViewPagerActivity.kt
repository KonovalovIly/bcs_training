package ru.konovalovily.notes.view

import android.annotation.SuppressLint
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }


    private fun initViewPager() {
        binding.viewPager2.adapter = adapter

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel.noteData.observe(
            this, {
                updateData(it)
            }
        )

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

}