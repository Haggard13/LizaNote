package ru.eh13.lizanote.notes.ui.mapper

import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.feature.notes.ui.mapper.NoteToAnnotatedStringMapper
import ru.eh13.lizanote.notes.ui.model.uiModel.PreviewNoteUiModel

class NoteToPreviewNoteUiModelMapper(
    private val noteTextToAnnotatedStringMapper: NoteToAnnotatedStringMapper
) {
    fun map(note: Note) =
        PreviewNoteUiModel(
            id = note.id ?: error("Note should have ID"),
            name = note.name,
            text = noteTextToAnnotatedStringMapper.map(note)
        )
}