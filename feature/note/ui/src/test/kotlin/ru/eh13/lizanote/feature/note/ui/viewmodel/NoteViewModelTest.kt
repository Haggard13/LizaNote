package ru.eh13.lizanote.feature.note.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import ru.eh13.lizanote.feature.note.domain.NoteRepository
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.feature.note.domain.usecase.NoteUseCaseImpl
import ru.eh13.lizanote.feature.notes.ui.mapper.NoteToAnnotatedStringMapper
import ru.eh13.lizanote.notes.ui.mapper.NoteToAnnotatedStringMapperImpl
import ru.eh13.lizanote.test.MainDispatcherRule
import ru.eh13.lizanote.ui.viewmodel.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<NoteRepository>() {
        coEvery { getNote(1) } returns Note(1)
    }


    @Test
    fun `when text changed then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem ipsum")))

        assertEquals(AnnotatedString("Lorem ipsum"), noteViewModel.uiState.textFieldValue.annotatedString)
    }

    @Test
    fun `when title changed then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTitleChanged("Lorem ipsum")

        assertEquals("Lorem ipsum", noteViewModel.uiState.title)
    }

    @Test
    fun `when bold clicked then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem Ipsum"), selection = TextRange(1, 4)))
        noteViewModel.onBoldSpanClicked()

        assertEquals(
            buildAnnotatedString {
                append("Lorem Ipsum")
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), 1, 2)
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), 2, 3)
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), 3, 4)
            },
            noteViewModel.uiState.textFieldValue.annotatedString
        )
    }

    @Test
    fun `when intalic clicked then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem Ipsum"), selection = TextRange(1, 4)))
        noteViewModel.onItalicSpanClicked()

        assertEquals(
            buildAnnotatedString {
                append("Lorem Ipsum")
                addStyle(SpanStyle(fontStyle = FontStyle.Italic), 1, 2)
                addStyle(SpanStyle(fontStyle = FontStyle.Italic), 2, 3)
                addStyle(SpanStyle(fontStyle = FontStyle.Italic), 3, 4)
            },
            noteViewModel.uiState.textFieldValue.annotatedString
        )
    }

    @Test
    fun `when underline clicked then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem Ipsum"), selection = TextRange(1, 4)))
        noteViewModel.onUnderlineSpanClicked()

        assertEquals(
            buildAnnotatedString {
                append("Lorem Ipsum")
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 1, 2)
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 2, 3)
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 3, 4)
            },
            noteViewModel.uiState.textFieldValue.annotatedString
        )
    }

    @Test
    fun `when strikethrough clicked then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem Ipsum"), selection = TextRange(1, 4)))
        noteViewModel.onStrikethroughSpanClicked()

        assertEquals(
            buildAnnotatedString {
                append("Lorem Ipsum")
                addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 1, 2)
                addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 2, 3)
                addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 3, 4)
            },
            noteViewModel.uiState.textFieldValue.annotatedString
        )
    }

    @Test
    fun `when color clicked then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem Ipsum"), selection = TextRange(1, 4)))
        noteViewModel.onColorSpanClicked()

        assertEquals(
            buildAnnotatedString {
                append("Lorem Ipsum")
                addStyle(SpanStyle(color = Color.Black), 1, 2)
                addStyle(SpanStyle(color = Color.Black), 2, 3)
                addStyle(SpanStyle(color = Color.Black), 3, 4)
            },
            noteViewModel.uiState.textFieldValue.annotatedString
        )
    }

    @Test
    fun `when background clicked then state update`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())

        noteViewModel.onTextChanged(TextFieldValue(AnnotatedString("Lorem Ipsum"), selection = TextRange(1, 4)))
        noteViewModel.onBackgroundSpanClicked()

        assertEquals(
            buildAnnotatedString {
                append("Lorem Ipsum")
                addStyle(SpanStyle(background = Color.Black), 1, 2)
                addStyle(SpanStyle(background = Color.Black), 2, 3)
                addStyle(SpanStyle(background = Color.Black), 3, 4)
            },
            noteViewModel.uiState.textFieldValue.annotatedString
        )
    }

    @Test
    fun `when color selection clicked then open bottom sheet`() {
        val noteViewModel = NoteViewModel(1, NoteUseCaseImpl(repository), NoteToAnnotatedStringMapperImpl(), mockk())
        noteViewModel.onSelectColorClicked()
        assertTrue(noteViewModel.uiState.colorSelectorIsVisible)
    }
}