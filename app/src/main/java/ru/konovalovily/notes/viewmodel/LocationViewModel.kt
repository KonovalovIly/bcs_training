package ru.konovalovily.notes.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {

    val latitude: MutableLiveData<Double> = MutableLiveData()
    val longitude: MutableLiveData<Double> = MutableLiveData()

    fun requestLocationData(location: Location) {
        Log.d("TAGER", location.toString())
        latitude.value = location.latitude
        longitude.value = location.longitude
    }
}