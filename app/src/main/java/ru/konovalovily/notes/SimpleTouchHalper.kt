package ru.konovalovily.notes

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.konovalovily.notes.contracts.MainContract
import ru.konovalovily.notes.presenter.MainPresenter
import ru.konovalovily.notes.view.MyItemRecyclerViewAdapter

class SimpleTouchHelper(
    private val presenter: MainPresenter,
    private val view: MainContract.View,
    private val adapter: MyItemRecyclerViewAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        presenter.deleteNote(position, view, adapter)
    }

}