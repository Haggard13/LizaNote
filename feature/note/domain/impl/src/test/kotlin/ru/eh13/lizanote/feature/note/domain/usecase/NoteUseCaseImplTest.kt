package ru.eh13.lizanote.feature.note.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import ru.eh13.lizanote.feature.note.domain.NoteRepository
import ru.eh13.lizanote.feature.note.domain.NoteUseCase
import ru.eh13.lizanote.feature.note.domain.addSpan
import ru.eh13.lizanote.feature.note.domain.deleteSpan
import ru.eh13.lizanote.feature.note.domain.entity.Note
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteUseCaseImplTest {
    private val spans =
        mapOf(
            1 to setOf(Note.Style.Underline),
            2 to setOf(Note.Style.Underline),
            3 to setOf(Note.Style.Underline),
        )
    private var note = Note(
        id = 1,
        name = "Note",
        text = "Hello, world!",
        spans = spans
    )
    private val repository: NoteRepository = mockk() {
        val noteSlot = slot<Note>()
        coEvery { getNote(1) } coAnswers { note }
        coEvery { save(capture(noteSlot)) } coAnswers {
            note = noteSlot.captured
        }
    }

    @Test
    fun `when call dispatchNote then note dispatched`() = runTest {
        val noteUseCase: NoteUseCase = NoteUseCaseImpl(repository)

        val note = noteUseCase.dispatchNote(1)

        assertEquals(repository.getNote(1), note)
    }

    @Test
    fun `when call updateText then text updated`() = runTest {
        val noteUseCase: NoteUseCase = NoteUseCaseImpl(repository)

        noteUseCase.dispatchNote(1)

        noteUseCase.updateText("Hello,world! new")

        noteUseCase.save()

        assertEquals(repository.getNote(1), noteUseCase.dispatchNote(1))

        noteUseCase.updateText("")

        noteUseCase.save()

        assertEquals(repository.getNote(1), noteUseCase.dispatchNote(1))
    }

    @Test
    fun `when call updateName then name updated`() = runTest {
        val noteUseCase: NoteUseCase = NoteUseCaseImpl(repository)

        noteUseCase.dispatchNote(1)
        noteUseCase.updateName("Note new")
        noteUseCase.save()

        assertEquals(repository.getNote(1), noteUseCase.dispatchNote(1))
    }

    @Test
    fun `when call deleteSpan then span deleted`() = runTest {
        val noteUseCase: NoteUseCase = NoteUseCaseImpl(repository)

        noteUseCase.dispatchNote(1)
        noteUseCase.deleteSpan<Note.Style.Underline>(start = 1, end = 3)
        noteUseCase.save()

        assertEquals(repository.getNote(1), noteUseCase.dispatchNote(1))
    }

    @Test
    fun `when call addSpan then span added`() = runTest {
        val noteUseCase: NoteUseCase = NoteUseCaseImpl(repository)

        noteUseCase.dispatchNote(1)
        noteUseCase.addSpan(start = 5, end = 7, style = Note.Style.Strikethrough)
        noteUseCase.save()

        assertEquals(
            repository.getNote(1),
            noteUseCase.dispatchNote(1)
        )

    }

    @Test
    fun `when call clearSpans then spans cleared`() = runTest {
        val noteUseCase: NoteUseCase = NoteUseCaseImpl(repository)

        noteUseCase.dispatchNote(1)
        noteUseCase.clearSpans(start = 1, end = 2)
        noteUseCase.save()

        assertEquals(
            repository.getNote(1),
            noteUseCase.dispatchNote(1)
        )
    }
}