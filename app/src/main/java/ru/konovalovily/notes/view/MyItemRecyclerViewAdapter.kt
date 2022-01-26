package ru.konovalovily.notes.view

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.card.MaterialCardView
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.callback.NoteItemDiffCallback
import ru.konovalovily.notes.contracts.FragmentOpener
import ru.konovalovily.notes.databinding.NoteItemBinding


class MyItemRecyclerViewAdapter(private val activity: FragmentOpener?) :
    ListAdapter<NoteModel, MyItemRecyclerViewAdapter.ViewHolder>(NoteItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun getIdItem(position: Int) = getItem(position).id

    inner class ViewHolder(binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tvTitleNote: TextView = binding.tvTitleNote
        private val tvTextNote: TextView = binding.tvTextNote
        private val cardView: MaterialCardView = binding.mcvNoteItem

        fun bind(item: NoteModel) {
            tvTitleNote.text = item.title
            tvTextNote.text = item.text

            cardView.setOnClickListener {
                activity?.openFragment(
                    R.id.fragment_container_view,
                    NoteDescriptionFragment.newInstance(item)
                )
            }
        }
    }
}