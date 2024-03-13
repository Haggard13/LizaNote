package ru.eh13.lizanote.feature.note.di

import ru.eh13.lizanote.feature.note.domain.NoteRepository
import ru.eh13.lizanote.feature.note.domain.NoteUseCase
import ru.eh13.lizanote.feature.note.domain.usecase.NoteUseCaseImpl

interface NoteUseCaseModule {
    val noteUseCase: NoteUseCase
}

class NoteUseCaseModuleImpl(private val deps: Deps) : NoteUseCaseModule {
    override val noteUseCase: NoteUseCase
        get() = NoteUseCaseImpl(deps.noteRepository)

    interface Deps {
        val noteRepository: NoteRepository
    }

}