package ru.konovalovily.notes.di

import org.koin.dsl.module
import ru.konovalovily.notes.model.NoteDatabase

val dataModule = module {

    single<NoteDatabase> {
        NoteDatabase.getInstance(context = get())
    }

}