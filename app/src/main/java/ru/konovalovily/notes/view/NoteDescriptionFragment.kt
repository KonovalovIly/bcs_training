package ru.konovalovily.notes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.databinding.FragmentNoteDescriptionBinding


class NoteDescriptionFragment : Fragment() {

    private var binding: FragmentNoteDescriptionBinding? = null

    private var item: NoteModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (arguments != null && requireArguments().containsKey(Constant.TITLE_TAG)) {
            item = requireArguments().getSerializable(Constant.TITLE_TAG) as NoteModel?
        }
        binding = FragmentNoteDescriptionBinding
            .inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            date.text = item?.getData()
            title.text = item?.getTitle()
            text.text = item?.getText()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (activity != null) run {
            val myActivity: MainActivity = activity as MainActivity
            myActivity.hideHomeButton()
        }
    }

    companion object {

        fun newInstance(): NoteDescriptionFragment = NoteDescriptionFragment()
    }

}