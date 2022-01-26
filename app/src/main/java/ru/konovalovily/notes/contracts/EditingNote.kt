package ru.konovalovily.notes.contracts

import ru.konovalovily.notes.view.NoteDescriptionFragment

interface EditingNote {

    var currentFragment: NoteDescriptionFragment?

    fun onBack() {
        currentFragment?.onBackButton()
    }

    fun onShare() {
        currentFragment?.onShareButton()
    }

    fun onUpdate() {
        currentFragment?.onUpdateButton()
    }
}