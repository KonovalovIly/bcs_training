package ru.konovalovily.notes.contracts

import androidx.fragment.app.Fragment

interface FragmentOpener {

    fun openFragment(resId: Int, classFragment: Fragment)
}