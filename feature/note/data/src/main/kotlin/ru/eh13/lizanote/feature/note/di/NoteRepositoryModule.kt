package ru.eh13.lizanote.feature.note.di

import ru.eh13.lizanote.feature.note.data.api.NoteDao
import ru.eh13.lizanote.feature.note.data.mapper.DtoToNoteMapper
import ru.eh13.lizanote.feature.note.data.mapper.NoteToDtoMapper
import ru.eh13.lizanote.feature.note.data.mapper.SpansDeserializer
import ru.eh13.lizanote.feature.note.data.mapper.SpansSerializer
import ru.eh13.lizanote.feature.note.data.repository.NoteRepositoryImpl
import ru.eh13.lizanote.feature.note.domain.NoteRepository
import ru.eh13.lizanote.feature.note.domain.NotesRepository

interface NoteRepositoryModule {
    val noteRepository: NoteRepository
    val notesRepository: NotesRepository
}

class NoteRepositoryModuleImpl(private val deps: Deps) : NoteRepositoryModule {
    private val serializer get() = SpansSerializer()
    private val deserializer get() = SpansDeserializer()
    private val toEntityMapper get() = DtoToNoteMapper(spansDeserializer = deserializer)
    private val toDtoMapper get() = NoteToDtoMapper(spansSerializer = serializer)

    override val noteRepository = NoteRepositoryImpl(
        toDtoMapper = toDtoMapper,
        toEntityMapper = toEntityMapper,
        dao = deps.noteDao
    )
    override val notesRepository get() = noteRepository

    interface Deps {
        val noteDao: NoteDao
    }
}