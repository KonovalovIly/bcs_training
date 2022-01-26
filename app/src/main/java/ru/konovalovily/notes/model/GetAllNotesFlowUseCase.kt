package ru.konovalovily.notes.model

class GetAllNotesFlowUseCase(private val repository: NoteRepository) {

    operator fun invoke() = repository.getNotesForBackup()
}