package ru.konovalovily.notes.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.konovalovily.notes.databinding.FragmentItemListBinding
import ru.konovalovily.notes.presenter.MainPresenter


class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val presenter = MainPresenter()
            list.adapter = MyItemRecyclerViewAdapter(presenter.noteData(), activity as? MainActivity)
            fabAddNote.setOnClickListener {
                requireActivity()
                    .startActivity(Intent(activity?.baseContext, EditActivity::class.java))
            }
        }
    }

    companion object {
        fun newInstance(): ItemFragment = ItemFragment()
    }
}