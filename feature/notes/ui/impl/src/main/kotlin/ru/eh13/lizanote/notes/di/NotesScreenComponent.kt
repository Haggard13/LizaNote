package ru.eh13.lizanote.notes.di

import androidx.lifecycle.ViewModel
import ru.eh13.lizanote.notes.ui.viewmodel.NotesViewModel
import kotlin.properties.Delegates

abstract class NotesScreenComponent internal constructor() : ViewModel() {

    abstract val viewModel: NotesViewModel

    interface Deps : NotesViewModelModuleImpl.Deps

    companion object DepsStore {
        var deps by Delegates.notNull<Deps>()
    }
}

fun NotesScreenComponentImpl(): NotesScreenComponent {
    val noteTextToAnnotatedStringMapperModule = NoteToAnnotatedStringMapperModuleImpl()
    return object : NotesScreenComponent(),
        NoteToAnnotatedStringMapperModule by noteTextToAnnotatedStringMapperModule,
        NotesViewModelModule by NotesViewModelModuleImpl(deps, noteTextToAnnotatedStringMapperModule) {}
}