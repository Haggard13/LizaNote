package ru.eh13.lizanote.notes.ui.viewmodel

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import ru.eh13.lizanote.feature.note.domain.NotesUseCase
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.flow.Flows
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.notes.ui.mapper.NoteToAnnotatedStringMapperImpl
import ru.eh13.lizanote.notes.ui.mapper.NoteToPreviewNoteUiModelMapper
import ru.eh13.lizanote.test.MainDispatcherRule
import kotlin.test.Test
import kotlin.test.assertEquals

class NotesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val router = mockk<Router>() {
        every { navigateToFlow(any()) } returns Unit
        every { navigateToFlow(any(), any()) } returns Unit
    }
    private val notes = listOf(
        Note(id = 1, name = "", text = ""),
        Note(id = 2, name = "name1", text = "text1"),
        Note(id = 3, name = "name2", text = "text2"),
    )
    private val mapper = NoteToPreviewNoteUiModelMapper(NoteToAnnotatedStringMapperImpl())
    private val useCase = mockk<NotesUseCase>() { coEvery { getNotes() } returns notes }

    @Test
    fun `when note clicked then navigate to Note flow`() {
        val viewModel = NotesViewModel(useCase, mapper, router)

        viewModel.onNoteClicked(1)

        verify { router.navigateToFlow(eq(Flows.Note), eq(1L)) }
    }

    @Test
    fun `when add note clicked then navigate to Note flow`() {
        val viewModel = NotesViewModel(useCase, mapper, router)

        viewModel.onAddNoteClicked()

        verify { router.navigateToFlow(eq(Flows.Note)) }
    }

    @Test
    fun `when NotesViewModel created then state contains notes from use case`() = runTest {
        val viewModel = NotesViewModel(useCase, mapper, router)

        delay(1)

        assertEquals(notes.map { mapper.map(it) }, viewModel.uiState.notes)
    }
}