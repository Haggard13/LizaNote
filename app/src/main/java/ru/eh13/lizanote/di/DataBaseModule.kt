package ru.eh13.lizanote.di

import android.content.Context
import ru.eh13.lizanote.db.LizaNoteDataBase
import ru.eh13.lizanote.feature.note.data.api.NoteDao
import ru.eh13.lizanote.feature.note.di.NoteRepositoryModuleImpl

interface DataBaseModule : NoteRepositoryModuleImpl.Deps {
    override val noteDao: NoteDao
}

class DataBaseModuleImpl(private val deps: Deps) : DataBaseModule {
    private val dataBase get() = LizaNoteDataBase.getInstance(deps.context)
    override val noteDao get() = dataBase.noteDao()

    interface Deps {
        val context: Context
    }
}

