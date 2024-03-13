package ru.eh13.lizanote.feature.note.domain

import ru.eh13.lizanote.feature.note.domain.entity.Note

interface NoteRepository {
    suspend fun getNote(id: Long): Note
    suspend fun save(note: Note)
}