package ru.konovalovily.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addToolbar()

        initField()
    }

    private fun addToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initField() = with(binding) {
        titleInMain.text = intent.getStringExtra(Constant.TITLE_TAG)
        textInMain.text = intent.getStringExtra(Constant.TEXT_TAG)
    }
}