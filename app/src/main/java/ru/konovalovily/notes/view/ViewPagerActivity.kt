package ru.konovalovily.notes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.konovalovily.notes.R
import ru.konovalovily.notes.databinding.ActivityViewPagerBinding
import ru.konovalovily.notes.model.NoteDatabase
import ru.konovalovily.notes.presenter.MainPresenter

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initViewPager()
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initViewPager() {
        val presenter = MainPresenter(NoteDatabase.getInstance(this))
        binding.viewPager2.adapter = ViewPagerAdapter(presenter.noteData())
    }
}