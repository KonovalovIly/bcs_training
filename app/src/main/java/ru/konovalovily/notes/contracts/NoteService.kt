package ru.konovalovily.notes.contracts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.konovalovily.notes.NoteModel

interface NoteService {

    @GET(GET_REQUEST)
    fun getNote(
        @Query("alt") alt: String,
        @Query("token") token: String
    ): Call<NoteModel>

    companion object{
        private const val GET_REQUEST = "note.json"
    }
}