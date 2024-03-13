package ru.eh13.lizanote.feature.note.domain.usecase

import ru.eh13.lizanote.feature.note.domain.NotesRepository
import ru.eh13.lizanote.feature.note.domain.NotesUseCase

class NotesUseCaseImpl(private val dataSource: NotesRepository) : NotesUseCase {
    override suspend fun observeNotes() = dataSource.observeNotes()
}