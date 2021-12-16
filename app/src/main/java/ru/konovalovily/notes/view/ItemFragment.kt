package ru.konovalovily.notes.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.SimpleTouchHelper
import ru.konovalovily.notes.contracts.FragmentOpener
import ru.konovalovily.notes.databinding.FragmentItemListBinding
import ru.konovalovily.notes.viewmodel.MainViewModel


class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: MyItemRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        adapter = MyItemRecyclerViewAdapter(emptyList(), activity as? FragmentOpener)
        binding.list.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.noteData.observe(
            viewLifecycleOwner, {
                updateData(it)
            }
        )

        binding.apply {
            btnViewPager.setOnClickListener {
                startActivity(Intent(activity?.baseContext, ViewPagerActivity::class.java))
            }
            fabAddNote.setOnClickListener {
                activity?.apply {
                    startActivity(Intent(activity?.baseContext, EditActivity::class.java))
                }
            }
        }
        initItemTouchHelper()
    }

    private fun initItemTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(SimpleTouchHelper(viewModel, adapter))
        itemTouchHelper.attachToRecyclerView(binding.list)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(noteList: List<NoteModel>) {
        adapter.updateData(noteList)
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): ItemFragment = ItemFragment()
    }
}