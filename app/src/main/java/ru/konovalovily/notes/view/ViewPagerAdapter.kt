package ru.konovalovily.notes.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.konovalovily.notes.NoteModel

class ViewPagerAdapter(
    private var values: List<NoteModel>,
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {

    fun updateData(values: List<NoteModel>) {
        this.values = values
    }

    override fun getItemCount(): Int = values.size

    override fun createFragment(position: Int): Fragment =
        NoteDescriptionFragment.newInstance(values[position])

}