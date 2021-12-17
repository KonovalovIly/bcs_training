package ru.konovalovily.notes.viewmodel

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.verify
import ru.konovalovily.notes.model.NoteRepository

@RunWith(JUnit4::class)
class EditViewModelTest {

    private lateinit var repository: NoteRepository
    private lateinit var viewModel: EditViewModel

    @Before
    fun setUp() {
        repository = Mockito.mock(NoteRepository::class.java)
        viewModel = EditViewModel(repository)
    }

    @Test
    fun testSaveNote() {
        viewModel.saveNote("text", "note")
        verify(repository).saveNote("text", "note")
    }

}