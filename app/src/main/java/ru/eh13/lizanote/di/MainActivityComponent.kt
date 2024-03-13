package ru.eh13.lizanote.di

import androidx.lifecycle.ViewModel
import ru.eh13.lizanote.feature.note.di.NoteFeatureComponent
import ru.eh13.lizanote.feature.note.di.NoteRepositoryModule
import ru.eh13.lizanote.feature.note.di.NoteRepositoryModuleImpl
import ru.eh13.lizanote.feature.notes.di.NotesFeatureComponent
import ru.eh13.lizanote.notes.di.NotesScreenComponent
import kotlin.properties.Delegates.notNull

abstract class MainActivityComponent : ViewModel(),
    NotesFeatureComponent.Deps,
    NoteFeatureComponent.Deps {

    init {
        NotesFeatureComponent.deps = this
        NoteFeatureComponent.deps = this
    }

    interface Deps : DataBaseModuleImpl.Deps

    companion object DepsStore {
        var deps: Deps by notNull()
    }
}

fun MainActivityComponentImpl(): MainActivityComponent {
    val dataBaseModule = DataBaseModuleImpl(MainActivityComponent.deps)
    return object : MainActivityComponent(),
        NavigationModule by NavigationModuleImpl(),
        DataBaseModule by dataBaseModule,
        NoteRepositoryModule by NoteRepositoryModuleImpl(dataBaseModule) {}
}