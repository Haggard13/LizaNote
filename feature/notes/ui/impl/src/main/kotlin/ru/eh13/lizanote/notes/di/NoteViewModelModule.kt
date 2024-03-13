package ru.eh13.lizanote.notes.di

import ru.eh13.lizanote.feature.note.domain.NotesRepository
import ru.eh13.lizanote.feature.note.domain.NotesUseCase
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.notes.ui.mapper.NoteToPreviewNoteUiModelMapper
import ru.eh13.lizanote.notes.ui.viewmodel.NotesViewModel

interface NotesViewModelModule {
    val viewModel: NotesViewModel
}

class NotesViewModelModuleImpl(
    private val deps: Deps,
    private val noteToAnnotatedStringMapperModule: NoteToAnnotatedStringMapperModule
) : NotesViewModelModule{
    override val viewModel get() = NotesViewModel(
        notesUseCase = deps.notesUseCase,
        mapper = NoteToPreviewNoteUiModelMapper(noteToAnnotatedStringMapperModule.noteTextToAnnotatedStringMapper),
        router = deps.router
    )

    interface Deps {
        val notesRepository: NotesRepository
        val notesUseCase: NotesUseCase
        val router: Router
    }
}