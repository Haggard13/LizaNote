package ru.eh13.lizanote.feature.note.data.mapper

import ru.eh13.lizanote.feature.note.data.model.NoteDto
import ru.eh13.lizanote.feature.note.domain.entity.Note

internal class DtoToNoteMapper(private val spansDeserializer: SpansDeserializer) {
    fun map(note: NoteDto): Note {
        return Note(
            id = note.id,
            name = note.name,
            text = note.text,
            spans = spansDeserializer.deserialize(note.spans)
        )
    }
}