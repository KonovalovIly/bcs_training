package ru.konovalovily.notes.di

import org.koin.dsl.module
import ru.konovalovily.notes.model.NetworkRepository
import ru.konovalovily.notes.model.NoteRepository

val domainModule = module {

    single<NoteRepository> {
        NoteRepository(noteDatabase = get())
    }

    single<NetworkRepository> {
        NetworkRepository(get())
    }

}