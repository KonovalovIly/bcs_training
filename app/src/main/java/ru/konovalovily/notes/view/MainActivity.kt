package ru.konovalovily.notes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.FragmentOpener
import ru.konovalovily.notes.contracts.IconDisplay
import ru.konovalovily.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IconDisplay, FragmentOpener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fragmentHolder: FragmentContainerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addToolbar()
        initField()
        if (savedInstanceState == null) openRecyclerViewFragment(
            fragmentHolder.id,
            ItemFragment.newInstance()
        )
    }

    private fun addToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    private fun initField() {
        fragmentHolder = binding.fragmentContainerView
    }

    override fun openFragment(resId: Int, classFragment: Fragment) {
        displayHomeButton()
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