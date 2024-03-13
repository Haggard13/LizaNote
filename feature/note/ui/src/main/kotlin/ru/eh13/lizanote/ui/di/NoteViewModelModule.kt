package ru.eh13.lizanote.ui.di

import ru.eh13.lizanote.feature.note.domain.NoteUseCase
import ru.eh13.lizanote.feature.notes.ui.mapper.NoteToAnnotatedStringMapper
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.ui.viewmodel.NoteViewModel

interface NoteViewModelModule {
    fun viewModel(args: Long?): NoteViewModel
}

class NoteViewModelModuleImpl(private val deps: Deps) : NoteViewModelModule {
    override fun viewModel(args: Long?): NoteViewModel = NoteViewModel(
        noteId = args,
        noteUseCase = deps.noteUseCase,
        noteToAnnotatedStringMapper = deps.noteTextToAnnotatedStringMapper,
        router = deps.router
    )

    interface Deps {
        val router: Router
        val noteUseCase: NoteUseCase
        val noteTextToAnnotatedStringMapper: NoteToAnnotatedStringMapper
    }
}