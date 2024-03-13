package ru.eh13.lizanote.feature.note.domain.usecase

import kotlinx.coroutines.flow.*
import ru.eh13.lizanote.feature.note.domain.NoteRepository
import ru.eh13.lizanote.feature.note.domain.NoteUseCase
import ru.eh13.lizanote.feature.note.domain.entity.Note
import kotlin.reflect.KClass

class NoteUseCaseImpl(private val repository: NoteRepository) : NoteUseCase {

    private val _note = MutableStateFlow<Note>(Note())

    override suspend fun dispatchNote(id: Long?): Note {
        id?.let { _note.value = repository.getNote(id) }
        return _note.value
    }

    override fun updateText(newText: String): Note {
        _note.update { it.withText(newText) }
        return _note.value
    }

    override fun updateName(newName: String): Note {
        _note.update { it.copy(name = newName) }
        return _note.value
    }

    override fun <S : Note.Style> deleteSpan(start: Int, end: Int, style: KClass<S>): Note {
        _note.update { it.withoutStyleInRange(start, end, style) }
        return _note.value
    }

    override fun <S : Note.Style> addSpan(start: Int, end: Int, style: S, styleClass: KClass<S>): Note {
        _note.update { it.withStyleInRange<S>(start, end, style, styleClass) }
        return _note.value
    }

    override fun clearSpans(start: Int, end: Int): Note {
        _note.update { it.withoutSpansInRange(start, end) }
        return _note.value
    }

    override suspend fun save() = repository.save(_note.value)
}