package ru.eh13.lizanote.ui.model.event

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import ru.eh13.lizanote.core.viewmodel.UiEvent
import ru.eh13.lizanote.ui.model.uiModel.HsvColor

sealed interface NoteUiEvent : UiEvent {
    class TextChanged(val newValue: TextFieldValue) : NoteUiEvent
    class TitleChanged(val newValue: String) : NoteUiEvent
    class ColorSelected(val newValue: HsvColor) : NoteUiEvent
    object BoldSpanClicked : NoteUiEvent
    object ItalicSpanClicked : NoteUiEvent
    object UnderlineSpanClicked : NoteUiEvent
    object StrikethroughSpanClicked : NoteUiEvent
    object ColorSpanClicked : NoteUiEvent
    object BackgroundSpanClicked : NoteUiEvent
    object ResetColorSpanClicked : NoteUiEvent
    object SelectColorClicked : NoteUiEvent
    object ClearSpansClicked : NoteUiEvent
    object DismissColorSelectorRequested : NoteUiEvent
    object ApplyButtonClicked : NoteUiEvent
    object UndoClicked : NoteUiEvent
    object DoClicked : NoteUiEvent
}