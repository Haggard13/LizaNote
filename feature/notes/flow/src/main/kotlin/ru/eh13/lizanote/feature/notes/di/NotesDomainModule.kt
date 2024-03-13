package ru.eh13.lizanote.feature.notes.di

import ru.eh13.lizanote.feature.note.domain.NotesRepository
import ru.eh13.lizanote.feature.note.domain.NotesUseCase
import ru.eh13.lizanote.feature.note.domain.usecase.NotesUseCaseImpl

internal interface NotesDomainModule {
    val notesUseCase: NotesUseCase
}

class NotesDomainModuleImpl(private val deps: Deps) : NotesDomainModule {
    override val notesUseCase get() = NotesUseCaseImpl(deps.notesRepository)

    interface Deps {
        val notesRepository: NotesRepository
    }
}