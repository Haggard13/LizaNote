package ru.eh13.lizanote.feature.note.di

import androidx.lifecycle.ViewModel
import ru.eh13.lizanote.feature.note.di.NoteFeatureComponent.DepsStore.deps
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.notes.di.NoteToAnnotatedStringMapperModule
import ru.eh13.lizanote.notes.di.NoteToAnnotatedStringMapperModuleImpl
import ru.eh13.lizanote.ui.di.NoteScreenComponent
import kotlin.properties.Delegates

abstract class NoteFeatureComponent internal constructor() : NoteScreenComponent.Deps, ViewModel() {

    interface Deps : NoteUseCaseModuleImpl.Deps {
        val router: Router
    }

    companion object DepsStore {
        var deps: Deps by Delegates.notNull()
    }
}

internal fun NoteFeatureComponentImpl(): NoteFeatureComponent {

    return object : NoteFeatureComponent(),
        NoteFeatureComponent.Deps by deps,
        NoteUseCaseModule by NoteUseCaseModuleImpl(deps),
        NoteToAnnotatedStringMapperModule by NoteToAnnotatedStringMapperModuleImpl() {

        init { NoteScreenComponent.deps = this }
    }
}

interface ErrorView
interface LoadingView