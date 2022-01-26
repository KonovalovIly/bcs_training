package ru.konovalovily.notes.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.SimpleTouchHelper
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
        adapter = MyItemRecyclerViewAdapter()
        binding.list.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.noteData.observe(viewLifecycleOwner) {
            viewModel.updateCurrentList(it)
        }
        viewModel.currentNoteList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.apply {
            fabAddNote.setOnClickListener {
                activity?.apply {
                    startActivity(Intent(activity?.baseContext, EditActivity::class.java))
                }
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.filterNotes(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filterNotes(newText)
                    return true
                }
            })
        }
        adapter.onClick = {
            startActivity(ViewPagerActivity.newIntentEditItem(requireContext(), it))
        }
        initItemTouchHelper()
    }

    private fun initItemTouchHelper() {
        val itemTouchHelper = ItemTouchHelper(SimpleTouchHelper(viewModel, adapter))
        itemTouchHelper.attachToRecyclerView(binding.list)
    }

    companion object {
        fun newInstance(): ItemFragment = ItemFragment()
    }
}