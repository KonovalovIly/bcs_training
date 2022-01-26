package ru.konovalovily.notes.viewmodel

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.konovalovily.notes.model.NoteRepository
import org.mockito.Mockito.mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class MainViewModelTest {

    private lateinit var repository: NoteRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        repository = mock(NoteRepository::class.java)
        viewModel = MainViewModel(repository)
    }


    @Test
    fun testDeleteNote() {
        viewModel.deleteNote(1)
        Mockito.verify(repository).deleteNote(1)
    }

}