package ru.konovalovily.notes.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import ru.konovalovily.notes.SimpleTouchHelper
import ru.konovalovily.notes.contracts.FragmentOpener
import ru.konovalovily.notes.contracts.MainContract
import ru.konovalovily.notes.databinding.FragmentItemListBinding
import ru.konovalovily.notes.model.NoteDatabase
import ru.konovalovily.notes.presenter.MainPresenter


class ItemFragment : Fragment(), MainContract.View {

    private lateinit var binding: FragmentItemListBinding

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MyItemRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MainPresenter(NoteDatabase.getInstance(activity?.baseContext!!))
        binding.apply {
            adapter = MyItemRecyclerViewAdapter(presenter.noteData(), activity as? FragmentOpener)
            list.adapter = adapter
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
        val itemTouchHelper = ItemTouchHelper(SimpleTouchHelper(presenter, this, adapter))
        itemTouchHelper.attachToRecyclerView(binding.list)
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateData() {
        adapter.updateData(presenter.noteData())
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): ItemFragment = ItemFragment()
    }
}