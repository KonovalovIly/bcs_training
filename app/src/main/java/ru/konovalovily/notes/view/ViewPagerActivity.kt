package ru.konovalovily.notes.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.EditingNote
import ru.konovalovily.notes.contracts.IconDisplay
import ru.konovalovily.notes.databinding.ActivityViewPagerBinding
import ru.konovalovily.notes.viewmodel.MainViewModel

class ViewPagerActivity : AppCompatActivity(), EditingNote, IconDisplay {

    private lateinit var binding: ActivityViewPagerBinding

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var toolbar: Toolbar
    override lateinit var editButton: FloatingActionButton
    override lateinit var shareButton: FloatingActionButton
    override lateinit var updateButton: FloatingActionButton

    private var adapter: ViewPagerAdapter = ViewPagerAdapter(emptyList(), this)
    override var currentFragment: NoteDescriptionFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initViewPager()
        initFAB()
    }

    private fun initToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    private fun initViewPager() {
        binding.viewPager2.adapter = adapter

        viewModel.noteData.observe(
            this, {
                updateData(it)
            }
        )

    }

    private fun initFAB() {
        editButton = binding.fabEditNote
        shareButton = binding.fabShareNote
        updateButton = binding.fabUpdateNote
        editButton.setOnClickListener {
            onEdit()
        }
        shareButton.setOnClickListener {
            onShare()
        }
        updateButton.setOnClickListener {
            onUpdate()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(noteList: List<NoteModel>) {
        adapter.updateData(noteList)
        adapter.notifyDataSetChanged()
    }

    override fun displayHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun hideHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}