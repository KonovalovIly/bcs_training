package ru.konovalovily.notes.view

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.contracts.EditingNote
import ru.konovalovily.notes.contracts.IconDisplay
import ru.konovalovily.notes.databinding.FragmentNoteDescriptionBinding
import ru.konovalovily.notes.viewmodel.UpdateViewModel
import java.util.*


class NoteDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentNoteDescriptionBinding

    private val viewModel by viewModel<UpdateViewModel>()

    private var item: NoteModel? = null
    private lateinit var textField: AppCompatEditText
    private lateinit var titleField: AppCompatEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (arguments != null && arguments?.containsKey(Constant.TITLE_TAG) == true) {
            item = arguments?.getParcelable(Constant.TITLE_TAG) as? NoteModel?
        }
        (activity as? IconDisplay)?.apply {
            displayEditButton()
            displayHomeButton()
            displayShareButton()
        }
        binding = FragmentNoteDescriptionBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textField = text
            titleField = title
            date.text = item?.data
        }
        textField.setText(item?.text)
        titleField.setText(item?.title)
    }

    fun onEditButton() {
        (activity as IconDisplay).apply {
            hideEditButton()
            displayUpdateButton()
        }
        textField.isEnabled = true
        titleField.isEnabled = true
    }

    fun onShareButton() {
        activity?.startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constant.SHARE_TYPE
            putExtra(
                Intent.EXTRA_TEXT, "${titleField.text} \n ${textField.text}"
            )
        })
    }

    fun onUpdateButton() {
        if (item != null) {
            item?.id?.let {
                NoteModel(
                    it,
                    titleField.text.toString(),
                    textField.text.toString(),
                    DateFormat.getDateFormat(context).format(Date())
                )
            }?.let {
                viewModel.updateNote(
                    it
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? EditingNote)?.currentFragment = this
        (activity as? IconDisplay)?.apply {
            displayEditButton()
            hideUpdateButton()
        }
        textField.isEnabled = false
        titleField.isEnabled = false
    }

    companion object {

        fun newInstance(note: NoteModel): NoteDescriptionFragment =
            NoteDescriptionFragment().apply {
                arguments = Bundle().apply { putParcelable(Constant.TITLE_TAG, note) }
            }
    }

}