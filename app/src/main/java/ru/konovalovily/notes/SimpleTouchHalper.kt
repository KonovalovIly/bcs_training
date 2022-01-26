package ru.konovalovily.notes

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.konovalovily.notes.view.MyItemRecyclerViewAdapter
import ru.konovalovily.notes.viewmodel.MainViewModel

class SimpleTouchHelper(
    private val viewModel: MainViewModel,
    private val adapter: MyItemRecyclerViewAdapter
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.bindingAdapterPosition
        viewModel.deleteNote(adapter.getIdItem(position))
    }

}