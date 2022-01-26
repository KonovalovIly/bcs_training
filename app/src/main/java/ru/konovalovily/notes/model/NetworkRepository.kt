package ru.konovalovily.notes.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.contracts.NoteService

class NetworkRepository(private val services: NoteService) {

    val note = MutableLiveData<NoteModel>()

    fun noteFromFirebase() {
        val listCall: Call<NoteModel> = services.getNote(ALT_REQUEST, TOKEN)

        listCall.enqueue(object : Callback<NoteModel> {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(
                call: Call<NoteModel>,
                response: Response<NoteModel>
            ) {
                note.value = response.body()
            }

            override fun onFailure(call: Call<NoteModel>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }
        })
    }

    companion object {
        private const val ALT_REQUEST = "media"
        private const val TOKEN = "070f0ef4-6e35-4895-9d1d-af5597d7e4f2"
    }
}