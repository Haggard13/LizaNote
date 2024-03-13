package ru.eh13.lizanote.notes.ui.model.uiModel

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.AnnotatedString

@Immutable
data class PreviewNoteUiModel(val id: Long, val name: String, val text: AnnotatedString)