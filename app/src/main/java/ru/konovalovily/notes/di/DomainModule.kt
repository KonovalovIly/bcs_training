package ru.konovalovily.notes.di

import org.koin.dsl.module
import ru.konovalovily.notes.model.NoteRepository

val domainModule = module {

    single<NoteRepository> {
        NoteRepository(context = get())
    }

}