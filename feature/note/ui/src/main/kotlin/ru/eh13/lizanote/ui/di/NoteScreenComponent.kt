package ru.eh13.lizanote.ui.di

import androidx.lifecycle.ViewModel
import ru.eh13.lizanote.ui.viewmodel.NoteViewModel
import kotlin.properties.Delegates

abstract class NoteScreenComponent internal constructor() : ViewModel() {

    abstract fun viewModel(args: Long?): NoteViewModel

    interface Deps : NoteViewModelModuleImpl.Deps

    companion object DepsStore {
        var deps by Delegates.notNull<Deps>()
    }
}

fun NoteScreenComponentImpl(): NoteScreenComponent {
    return object : NoteScreenComponent(),
        NoteViewModelModule by NoteViewModelModuleImpl(deps) {}
}