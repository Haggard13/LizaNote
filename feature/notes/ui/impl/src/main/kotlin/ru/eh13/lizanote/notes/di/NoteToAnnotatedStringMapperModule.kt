package ru.eh13.lizanote.notes.di

import ru.eh13.lizanote.feature.notes.ui.mapper.NoteToAnnotatedStringMapper
import ru.eh13.lizanote.notes.ui.mapper.NoteToAnnotatedStringMapperImpl

interface NoteToAnnotatedStringMapperModule {
    val noteTextToAnnotatedStringMapper: NoteToAnnotatedStringMapper
}

class NoteToAnnotatedStringMapperModuleImpl : NoteToAnnotatedStringMapperModule {
    override val noteTextToAnnotatedStringMapper: NoteToAnnotatedStringMapper
        get() = NoteToAnnotatedStringMapperImpl()

}