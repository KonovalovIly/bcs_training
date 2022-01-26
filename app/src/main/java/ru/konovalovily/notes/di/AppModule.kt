package ru.konovalovily.notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.konovalovily.notes.model.GetAllNotesFlowUseCase
import ru.konovalovily.notes.viewmodel.*

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

    viewModel<DownloadViewModel> {
        DownloadViewModel(repository = get(), networkRepository = get())
    }

    factory<GetAllNotesFlowUseCase> {
        GetAllNotesFlowUseCase(repository = get())
    }

    viewModel<LocationViewModel> {
        LocationViewModel()
    }

}