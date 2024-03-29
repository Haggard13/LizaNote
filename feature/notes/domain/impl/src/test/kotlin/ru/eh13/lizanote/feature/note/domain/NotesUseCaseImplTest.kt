package ru.eh13.lizanote.feature.note.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.feature.note.domain.usecase.NotesUseCaseImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class NotesUseCaseImplTest {
    @Test
    fun `when data source return empty list then usecase return empty list`() = runTest {
        val dataSource = mockk<NotesRepository>()
        coEvery { dataSource.observeNotes() } returns flowOf(emptyList())

        val noteUseCaseImpl: NotesUseCase = NotesUseCaseImpl(dataSource)

        assertEquals(emptyList(), noteUseCaseImpl.observeNotes().last())
    }

    @Test
    fun `when data source return notes then usecase return notes`() = runTest {
        val notes = listOf(
            Note(id = 1),
            Note(id = 2),
            Note(id = 3),
            Note(id = 4),
        )
        val dataSource = mockk<NotesRepository>()
        coEvery { dataSource.observeNotes() } returns flowOf(notes)

        val noteUseCaseImpl: NotesUseCase = NotesUseCaseImpl(dataSource)

        assertEquals(notes.toSet(), noteUseCaseImpl.observeNotes().last().toSet())
    }
}