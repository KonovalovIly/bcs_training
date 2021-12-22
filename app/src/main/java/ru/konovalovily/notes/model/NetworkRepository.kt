package ru.konovalovily.notes.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.NoteModel
import ru.konovalovily.notes.contracts.NoteService

class NetworkRepository {

    val note = MutableLiveData<NoteModel>()

    fun noteFromFirebase() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val services: NoteService = retrofit.create(NoteService::class.java)

        val listCall: Call<NoteModel> = services.getNote("media", Constant.TOKEN)

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
}