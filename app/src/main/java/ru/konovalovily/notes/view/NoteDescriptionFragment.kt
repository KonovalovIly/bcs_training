package ru.konovalovily.notes.view

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
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
    private lateinit var noteDescription: CardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (arguments != null && arguments?.containsKey(Constant.TITLE_TAG) == true) {
            item = arguments?.getParcelable(Constant.TITLE_TAG) as? NoteModel?
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
            noteDescription = cvNoteDescription
        }
        textField.setText(item?.text)
        titleField.setText(item?.title)
        noteDescription.setOnClickListener {
            editMode()
        }
    }

    fun onShareButton() {
        activity?.startActivity(Intent(Intent.ACTION_SEND).apply {
            type = Constant.SHARE_TYPE
            putExtra(
                Intent.EXTRA_TEXT, "${titleField.text} \n ${textField.text}"
            )
        })
    }

    fun onBackButton() {
        standartMode()
    }

    fun onUpdateButton() {
        standartMode()
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
        standartMode()
    }

    private fun editMode() {
        (activity as IconDisplay).displaySaveActionMode()
        textField.isEnabled = true
        titleField.isEnabled = true
        noteDescription.isEnabled = false
    }

    private fun standartMode() {
        (activity as IconDisplay).hideSaveActionMode()
        textField.isEnabled = false
        titleField.isEnabled = false
        binding.cvNoteDescription.isEnabled = true
    }

    companion object {

        fun newInstance(note: NoteModel): NoteDescriptionFragment =
            NoteDescriptionFragment().apply {
                arguments = Bundle().apply { putParcelable(Constant.TITLE_TAG, note) }
            }
    }

}