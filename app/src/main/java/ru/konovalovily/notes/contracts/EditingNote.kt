package ru.konovalovily.notes.contracts

import ru.konovalovily.notes.view.NoteDescriptionFragment

interface EditingNote {

    var currentFragment: NoteDescriptionFragment?

    fun onEdit() {
        currentFragment?.onEditButton()
    }

    fun onShare() {
        currentFragment?.onShareButton()
    }

    fun onUpdate() {
        currentFragment?.onUpdateButton()
    }
}