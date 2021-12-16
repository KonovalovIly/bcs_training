package ru.konovalovily.notes.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.databinding.ActivityViewPagerBinding
import ru.konovalovily.notes.viewmodel.MainViewModel

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    private val viewModel by viewModel<MainViewModel>()

    private var adapter: ViewPagerAdapter = ViewPagerAdapter(emptyList())

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
        binding.viewPager2.adapter = adapter

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

}