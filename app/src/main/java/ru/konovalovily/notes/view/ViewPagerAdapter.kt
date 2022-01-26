package ru.konovalovily.notes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.databinding.FragmentNoteDescriptionBinding

class ViewPagerAdapter(private var values: List<NoteModel>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {


    inner class PagerVH(private var binding: FragmentNoteDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NoteModel) {
            binding.title.text = item.title
            binding.text.text = item.text
            binding.date.text = item.data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            FragmentNoteDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

}