package ru.konovalovily.notes.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.databinding.NoteItemBinding
import ru.konovalovily.notes.presenter.MainNotePresenter


class MyItemRecyclerViewAdapter(
    private val values: ArrayList<NoteModel>,
    private val presenter: MainNotePresenter
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

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
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tvTitleNote: TextView = binding.tvTitleNote
        private val tvTextNote: TextView = binding.tvTextNote
        private val cardView: MaterialCardView = binding.mcvNoteItem

        fun bind(item: NoteModel) {
            tvTitleNote.text = item.getTitle()
            tvTextNote.text = item.getText()

            cardView.setOnClickListener {
                val bundle = Bundle().apply {
                    putSerializable(Constant.TITLE_TAG, item)
                }
                val fragmentToManager = NoteDescriptionFragment.newInstance()
                fragmentToManager.arguments = bundle
                presenter.addDescriptionFragment(
                    R.id.fragment_container_view,
                    fragmentToManager
                )
            }
        }
    }
}