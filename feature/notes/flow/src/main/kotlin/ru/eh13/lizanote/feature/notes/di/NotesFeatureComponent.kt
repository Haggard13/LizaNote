package ru.eh13.lizanote.feature.notes.di

import androidx.lifecycle.ViewModel
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.notes.di.NotesScreenComponent
import kotlin.properties.Delegates

abstract class NotesFeatureComponent internal constructor() : NotesScreenComponent.Deps, ViewModel() {

    interface Deps : NotesDomainModuleImpl.Deps {
        val router: Router
    }

    companion object DepsStore {
        var deps: Deps by Delegates.notNull()
    }
}

internal fun NotesFeatureComponentImpl(): NotesFeatureComponent {
    return object : NotesFeatureComponent(),
        NotesFeatureComponent.Deps by deps,
        NotesDomainModule by NotesDomainModuleImpl(deps) {

        init { NotesScreenComponent.deps = this }
    }
}