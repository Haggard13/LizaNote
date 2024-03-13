package ru.eh13.lizanote.feature.note.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import ru.eh13.lizanote.feature.note.data.api.NoteDao
import ru.eh13.lizanote.feature.note.data.mapper.DtoToNoteMapper
import ru.eh13.lizanote.feature.note.data.mapper.NoteToDtoMapper
import ru.eh13.lizanote.feature.note.domain.NoteRepository
import ru.eh13.lizanote.feature.note.domain.NotesRepository
import ru.eh13.lizanote.feature.note.domain.entity.Note

class NoteRepositoryImpl internal constructor(
    private val dao: NoteDao,
    private val toEntityMapper: DtoToNoteMapper,
    private val toDtoMapper: NoteToDtoMapper,
) : NoteRepository, NotesRepository {

    private val _notes = MutableStateFlow<List<Note>?>(null)

    override suspend fun observeNotes(): Flow<List<Note>> {
        if (_notes.value == null) {
            _notes.value = getNotes()
        }
        return _notes.mapNotNull { it }
    }

    override suspend fun getNote(id: Long) = withContext(Dispatchers.IO) {
        val dto = dao.getNote(id)
        toEntityMapper.map(dto)
    }

    override suspend fun save(note: Note) = withContext(Dispatchers.IO) {
        dao.save(toDtoMapper.map(note))
        _notes.value = getNotes()
    }

    private suspend fun getNotes() = dao.getNotes().map(toEntityMapper::map)
}