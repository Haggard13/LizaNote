package ru.eh13.lizanote.feature.note.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.eh13.lizanote.feature.note.domain.entity.Note

interface NotesUseCase {
    suspend fun observeNotes(): Flow<List<Note>>
}