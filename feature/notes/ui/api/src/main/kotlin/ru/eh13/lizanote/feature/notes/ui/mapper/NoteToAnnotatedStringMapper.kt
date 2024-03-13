package ru.eh13.lizanote.feature.notes.ui.mapper

import androidx.compose.ui.text.AnnotatedString
import ru.eh13.lizanote.feature.note.domain.entity.Note

interface NoteToAnnotatedStringMapper {
    fun map(note: Note): AnnotatedString
}