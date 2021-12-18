package ru.konovalovily.notes.contracts

import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface IconDisplay {

    var shareButton: FloatingActionButton
    var editButton: FloatingActionButton
    var updateButton: FloatingActionButton

    fun displayHomeButton()
    fun hideHomeButton()
    fun displayEditButton() {
        editButton.visibility = View.VISIBLE
    }

    fun hideEditButton() {
        editButton.visibility = View.GONE
    }

    fun displayShareButton() {
        shareButton.visibility = View.VISIBLE
    }

    fun hideShareButton() {
        shareButton.visibility = View.GONE
    }

    fun displayUpdateButton() {
        updateButton.visibility = View.VISIBLE
    }

    fun hideUpdateButton() {
        updateButton.visibility = View.GONE
    }

}