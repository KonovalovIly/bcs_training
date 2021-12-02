package ru.konovalovily.notes.view

import androidx.fragment.app.Fragment

interface MainNotesView {

    fun createFragment(resId: Int, classFragment: Fragment)
    fun addDescriptionFragment(resId: Int, classFragment: Fragment)

    fun displayHomeButton()
    fun hideHomeButton()
}