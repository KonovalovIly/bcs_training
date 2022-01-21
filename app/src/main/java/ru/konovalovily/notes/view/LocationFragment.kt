package ru.konovalovily.notes.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.konovalovily.notes.R
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.konovalovily.notes.databinding.FragmentLocationBinding
import ru.konovalovily.notes.viewmodel.LocationViewModel

class LocationFragment : Fragment() {

    private val viewModel by viewModel<LocationViewModel>()

    private var _binding: FragmentLocationBinding? = null
    private val binding: FragmentLocationBinding
        get() = _binding ?: throw RuntimeException("FragmentLocationBinding == null")

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentLocationBinding.inflate(inflater).also {
        _binding = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        observeViewModel()
        binding.fabGetLocation.setOnClickListener {
            requestPermission()
        }
    }

    private fun requestPermission() {
        if (!isPermissionEnabled()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        }
        if (isPermissionEnabled()) {
            if (isLocationEnabled()) subscribeOnLocation()
            else Toast.makeText(
                requireContext(),
                getString(R.string.location_unenabled),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("MissingPermission")
    private fun subscribeOnLocation() {

        val mLocationRequest = LocationRequest.create().apply {
            interval = INTERVAL
            fastestInterval = FASTEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = WAIT_TIME
        }

        val looper = Looper.getMainLooper()

        fusedLocationProviderClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            looper
        )
    }

    private fun observeViewModel() {
        viewModel.latitude.observe(viewLifecycleOwner) {
            binding.latitude.text = it.toString()
        }
        viewModel.longitude.observe(viewLifecycleOwner) {
            binding.longitude.text = it.toString()
        }
    }

    private fun isPermissionEnabled(): Boolean = ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            viewModel.requestLocationData(locationResult.lastLocation)
        }
    }

    companion object {
        private const val REQUEST_CODE = 101
        private const val WAIT_TIME: Long = 100
        private const val INTERVAL: Long = 100
        private const val FASTEST_INTERVAL: Long = 50


        @JvmStatic
        fun newInstance() = LocationFragment()
    }
}