package ru.konovalovily.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import ru.konovalovily.notes.databinding.ActivityMainBinding
import ru.konovalovily.notes.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainNotesView {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fragmentHolder: FragmentContainerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addToolbar()
        initField()
        if (savedInstanceState == null) presenter.createFragment(
            fragmentHolder.id,
            ItemFragment.newInstance()
        ) else displayHomeButton()
    }

    private fun addToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    private fun initField() {
        fragmentHolder = binding.fragmentContainerView
        presenter = MainPresenter(this)
    }

    override fun addDescriptionFragment(resId: Int, classFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            replace(resId, classFragment)
            commit()
        }
    }

    override fun createFragment(resId: Int, classFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(resId, classFragment)
            commit()
        }
    }

    override fun displayHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun hideHomeButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}