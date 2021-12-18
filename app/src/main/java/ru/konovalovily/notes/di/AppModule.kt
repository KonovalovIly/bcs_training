package ru.konovalovily.notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.konovalovily.notes.viewmodel.EditViewModel
import ru.konovalovily.notes.viewmodel.MainViewModel
import ru.konovalovily.notes.viewmodel.UpdateViewModel

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(repository = get())
    }

    viewModel<EditViewModel> {
        EditViewModel(repository = get())
    }

    viewModel<UpdateViewModel> {
        UpdateViewModel(repository = get())
    }

}