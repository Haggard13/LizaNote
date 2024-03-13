package ru.eh13.lizanote.ui.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.eh13.core.viewmodel.annotations.ViewModel
import ru.eh13.lizanote.core.viewmodel.BaseViewModel
import ru.eh13.lizanote.feature.note.domain.NoteUseCase
import ru.eh13.lizanote.feature.note.domain.addSpan
import ru.eh13.lizanote.feature.note.domain.clearSpans
import ru.eh13.lizanote.feature.note.domain.deleteSpan
import ru.eh13.lizanote.feature.note.domain.entity.*
import ru.eh13.lizanote.feature.notes.ui.mapper.NoteToAnnotatedStringMapper
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.ui.mapper.toNoteColor
import ru.eh13.lizanote.ui.model.event.NoteUiEvent
import ru.eh13.lizanote.ui.model.uiModel.HsvColor
import kotlin.math.pow

@ViewModel(NoteUiEvent::class)
class NoteViewModel(
    private val noteId: Long?,
    private val noteUseCase: NoteUseCase,
    private val noteToAnnotatedStringMapper: NoteToAnnotatedStringMapper,
    router: Router
) : BaseViewModel<NoteUiEvent>(router) {
    private val _uiState = MutableNoteUiState()
    val uiState = _uiState as NoteUiState

    init {
        viewModelScope.launch {
            val note = noteUseCase.dispatchNote(id = noteId)
            _uiState.title = note.name
            _uiState.textFieldValue = _uiState.textFieldValue.copy(
                annotatedString = noteToAnnotatedStringMapper.map(note)
            )
        }
    }

    override fun dispatchEvent(event: NoteUiEvent) {
        when (event) {
            is NoteUiEvent.TextChanged -> onTextChanged(event.newValue)
            is NoteUiEvent.TitleChanged -> onTitleChanged(event.newValue)
            is NoteUiEvent.ColorSelected -> _uiState.color = event.newValue
            NoteUiEvent.BoldSpanClicked -> reverseSpanForStyle(Note.Style.Bold) { it.fontWeight == FontWeight.Bold }
            NoteUiEvent.ItalicSpanClicked -> reverseSpanForStyle(Note.Style.Italic) { it.fontStyle == FontStyle.Italic }

            NoteUiEvent.UnderlineSpanClicked ->
                reverseSpanForStyle(Note.Style.Underline) { it.textDecoration == TextDecoration.Underline }

            NoteUiEvent.StrikethroughSpanClicked ->
                reverseSpanForStyle(Note.Style.Strikethrough) { it.textDecoration == TextDecoration.LineThrough }

            NoteUiEvent.ColorSpanClicked -> onColorSpanClicked()
            NoteUiEvent.BackgroundSpanClicked -> onBackgroundSpanClicked()
            NoteUiEvent.ClearSpansClicked -> onClearSpansClicked()
            NoteUiEvent.SelectColorClicked -> _uiState.colorSelectorIsVisible = true
            NoteUiEvent.ResetColorSpanClicked -> onResetColorSpansClicked()
            NoteUiEvent.DismissColorSelectorRequested -> _uiState.colorSelectorIsVisible = false
            NoteUiEvent.ApplyButtonClicked -> _uiState.colorSelectorIsVisible = false
            NoteUiEvent.DoClicked -> TODO()
            NoteUiEvent.UndoClicked -> TODO()
        }
    }

    private fun onTextChanged(newValue: TextFieldValue) {
        viewModelScope.launch {
            val newNote = noteUseCase.updateText(newValue.text)
            _uiState.textFieldValue = newValue.copy(noteToAnnotatedStringMapper.map(newNote))
        }
    }

    private fun onTitleChanged(newValue: String) {
        viewModelScope.launch {
            val newNote = noteUseCase.updateName(newValue)
            _uiState.title = newNote.name
        }
    }

    fun onColorSpanClicked() {
        val span = Note.Style.Colored(uiState.color.rgbColor.toNoteColor())
        val newNote = noteUseCase.addSpan(getSpanChangingRange(), span)
        dispatchNewNote(newNote)
    }

    private fun onBackgroundSpanClicked() {
        val span = Note.Style.Selected(uiState.color.rgbColor.toNoteColor())
        val newNote = noteUseCase.addSpan(getSpanChangingRange(), span)
        dispatchNewNote(newNote)
    }

    private fun onResetColorSpansClicked() {
        noteUseCase.deleteSpan<Note.Style.Colored>(getSpanChangingRange())
        val newNote = noteUseCase.deleteSpan<Note.Style.Selected>(getSpanChangingRange())
        dispatchNewNote(newNote)
    }

    private fun onClearSpansClicked() {
        val newNote = noteUseCase.clearSpans(getSpanChangingRange())
        dispatchNewNote(newNote)
    }

    private inline fun <reified S : Note.Style> reverseSpanForStyle(
        style: S,
        noinline styleFilter: (SpanStyle) -> Boolean
    ) {
        val start = uiState.selection.start
        val end = uiState.selection.end - 1
        val newNote = if (textHasAnyStyledCharInSelectedRange(styleFilter)) {
            noteUseCase.deleteSpan<S>(start, end)
        } else {
            noteUseCase.addSpan(start, end, style)
        }
        dispatchNewNote(newNote)
    }

    private fun textHasAnyStyledCharInSelectedRange(styleFilter: (SpanStyle) -> Boolean): Boolean {
        return uiState.text.spanStyles.any {
            styleFilter(it.item) && uiState.selection.intersects(TextRange(it.start, it.end))
        }
    }

    private fun dispatchNewNote(note: Note) {
        _uiState.textFieldValue = _uiState.textFieldValue.copy(noteToAnnotatedStringMapper.map(note))
    }

    private fun getSpanChangingRange() = _uiState.selection.start..(_uiState.selection.end - 1)

    override fun back() {
        viewModelScope.launch {
            noteUseCase.save()
            super.back()
        }
    }
}

@Stable
interface NoteUiState {
    val title: String
    val textFieldValue: TextFieldValue
    val colorSelectorIsVisible: Boolean
    val color: HsvColor
}

private val NoteUiState.selection get() = textFieldValue.selection
private val NoteUiState.text get() = textFieldValue.annotatedString

internal class MutableNoteUiState : NoteUiState {
    override var title by mutableStateOf("")
    override var textFieldValue by mutableStateOf(TextFieldValue())
    override var colorSelectorIsVisible by mutableStateOf(false)
    override var color by mutableStateOf(HsvColor())
}