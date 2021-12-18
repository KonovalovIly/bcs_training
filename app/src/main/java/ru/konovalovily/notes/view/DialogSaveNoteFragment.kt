package ru.konovalovily.notes.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.Saving

class DialogSaveNoteFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_message)
                .setPositiveButton(
                    R.string.positive
                ) { _, _ ->
                    (activity as Saving).saveNote()
                }.setNegativeButton(R.string.negative) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(true)
                .create()
        } ?: throw IllegalStateException("Exception")
    }
}