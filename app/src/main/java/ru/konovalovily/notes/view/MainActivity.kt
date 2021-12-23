package ru.konovalovily.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.callback.EditActionModeCallback
import ru.konovalovily.notes.callback.SaveActionModeCallback
import ru.konovalovily.notes.contracts.Downloading
import ru.konovalovily.notes.contracts.EditingNote
import ru.konovalovily.notes.contracts.FragmentOpener
import ru.konovalovily.notes.contracts.IconDisplay
import ru.konovalovily.notes.databinding.ActivityMainBinding
import ru.konovalovily.notes.viewmodel.DownloadViewModel

class MainActivity : AppCompatActivity(), IconDisplay, FragmentOpener, EditingNote, Downloading {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fragmentHolder: FragmentContainerView
    override var currentFragment: NoteDescriptionFragment? = null
    private val viewModel by viewModel<DownloadViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initField()
        if (savedInstanceState == null) openRecyclerViewFragment(
            fragmentHolder.id,
            ItemFragment.newInstance()
        )
    }

    private fun initField() {
        fragmentHolder = binding.fragmentContainerView

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.download -> {
                    onDownload()
                    true
                }
                else -> false
            }
        }
    }

    override fun openFragment(resId: Int, classFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            replace(resId, classFragment)
            commit()
        }
    }

    private fun openRecyclerViewFragment(resId: Int, classFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(resId, classFragment)
            commit()
        }
    }

    override fun displayEditActionMode() {
        startSupportActionMode(EditActionModeCallback(this))
    }

    override fun displaySaveActionMode() {
        startSupportActionMode(SaveActionModeCallback(this))
    }

    override fun onDownload() {
        viewModel.getNote()
        if (viewModel.noteFromFirebase.value == null) {
            viewModel.noteFromFirebase.observe(
                this, {
                    viewModel.saveNote(it)
                }
            )
        }
    }
}