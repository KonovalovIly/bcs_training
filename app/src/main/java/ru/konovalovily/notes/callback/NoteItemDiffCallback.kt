package ru.konovalovily.notes.callback

import androidx.recyclerview.widget.DiffUtil
import ru.konovalovily.notes.NoteModel

class NoteItemDiffCallback: DiffUtil.ItemCallback<NoteModel>() {

    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }
}