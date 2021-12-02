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

    private var binding: FragmentItemListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val presenter = MainPresenter(activity as MainActivity)
            list.adapter = MyItemRecyclerViewAdapter(presenter.getNoteData(), presenter)
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