package ru.konovalovily.notes.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.R
import ru.konovalovily.notes.callback.SaveActionModeCallback
import ru.konovalovily.notes.contracts.Downloadable
import ru.konovalovily.notes.contracts.EditingNote
import ru.konovalovily.notes.contracts.FragmentOpener
import ru.konovalovily.notes.contracts.IconDisplay
import ru.konovalovily.notes.databinding.ActivityMainBinding
import ru.konovalovily.notes.viewmodel.DownloadViewModel

class MainActivity : AppCompatActivity(), IconDisplay, FragmentOpener, EditingNote, Downloadable {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fragmentHolder: FragmentContainerView
    override var currentFragment: NoteDescriptionFragment? = null
    private val viewModel by viewModel<DownloadViewModel>()
    private var supportActionMode: ActionMode? = null

    private lateinit var cm: ConnectivityManager
    private lateinit var snackbar: Snackbar
    private var callback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            snackbar.dismiss()
        }

        override fun onLost(network: Network) {
            snackbar.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initField()
        if (savedInstanceState == null) openRecyclerViewFragment(
            fragmentHolder.id,
            ItemFragment.newInstance()
        )
        initNetworkCheck()
    }

    private fun initField() {
        fragmentHolder = binding.fragmentContainerView

        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.download -> {
                    onDownload()
                    true
                }
                else -> false
            }
        }
    }

    override fun openFragment(resId: Int, classFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            replace(resId, classFragment)
            commit()
        }
    }

    private fun openRecyclerViewFragment(resId: Int, classFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(resId, classFragment)
            commit()
        }
    }

    override fun displaySaveActionMode() {
        supportActionMode = startSupportActionMode(SaveActionModeCallback(this))
    }

    override fun hideSaveActionMode() {
        supportActionMode?.finish()
    }

    override fun onDownload() {
        viewModel.getNote()
        if (viewModel.noteFromFirebase.value == null) {
            viewModel.noteFromFirebase.observe(
                this, {
                    viewModel.saveNote(it)
                }
            )
        }
    }

    @SuppressLint("ShowToast")
    private fun initNetworkCheck() {
        cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder =
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        snackbar = Snackbar.make(
            binding.llMain,
            R.string.internet_unavailable,
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(R.string.hide) { snackbar.dismiss() }

        if (cm.isDefaultNetworkActive) snackbar.show()

        cm.registerNetworkCallback(
            builder.build(),
            callback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        cm.unregisterNetworkCallback(callback)
    }
}