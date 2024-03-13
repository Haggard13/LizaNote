package ru.eh13.lizanote.feature.note.data.mapper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import ru.eh13.lizanote.feature.note.data.model.NoteDto
import ru.eh13.lizanote.feature.note.domain.entity.Note

internal class NoteToDtoMapper(private val spansSerializer: SpansSerializer) {
    fun map(note: Note): NoteDto {
        return NoteDto(
            id = note.id ?: 0,
            name = note.name,
            text = note.text,
            spans = spansSerializer.serialize(note.spans)
        )
    }
}