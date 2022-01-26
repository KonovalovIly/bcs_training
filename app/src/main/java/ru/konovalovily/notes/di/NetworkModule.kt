package ru.konovalovily.notes.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.konovalovily.notes.Constant
import ru.konovalovily.notes.contracts.NoteService

val networkModule = module {
    factory { provideRetrofit() }
    single { provideNoteServiceApi(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideNoteServiceApi(retrofit: Retrofit): NoteService =
    retrofit.create(NoteService::class.java)