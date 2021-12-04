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

    private lateinit var binding: FragmentNoteDescriptionBinding

    private var item: NoteModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (arguments != null && arguments?.containsKey(Constant.TITLE_TAG) == true) {
            item = arguments?.getSerializable(Constant.TITLE_TAG) as? NoteModel?
        }
        binding = FragmentNoteDescriptionBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            date.text = item?.data
            title.text = item?.title
            text.text = item?.text
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (activity != null) run {
            (activity as? MainActivity)?.hideHomeButton()
        }
    }

    companion object {

        fun newInstance(): NoteDescriptionFragment = NoteDescriptionFragment()
    }

}