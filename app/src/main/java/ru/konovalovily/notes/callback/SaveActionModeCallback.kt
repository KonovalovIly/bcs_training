package ru.konovalovily.notes.callback

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.R
import ru.konovalovily.notes.contracts.EditingNote

class SaveActionModeCallback(val activity: EditingNote) : ActionMode.Callback {

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.save_toolbar_menu, menu)
        mode?.title = Constant.Note_EDIT
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.share -> {
                activity.onShare()
                true
            }
            R.id.update -> {
                activity.onUpdate()
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {}
}