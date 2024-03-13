package ru.eh13.lizanote.feature.note.domain

import ru.eh13.lizanote.feature.note.domain.entity.Note
import kotlin.reflect.KClass

interface NoteUseCase {
    suspend fun dispatchNote(id: Long?): Note
    fun updateText(newText: String): Note
    fun updateName(newName: String): Note
    fun <T : Note.Style> deleteSpan(start: Int, end: Int, style: KClass<T>): Note
    fun <T : Note.Style> addSpan(start: Int, end: Int, style: T, styleClass: KClass<T>): Note
    fun clearSpans(start: Int, end: Int): Note
    suspend fun save()
}

inline fun <reified S : Note.Style> NoteUseCase.deleteSpan(start: Int, end: Int) =
    deleteSpan(start, end, S::class)

inline fun <reified S : Note.Style> NoteUseCase.deleteSpan(range: IntRange) =
    deleteSpan<S>(range.start, range.endInclusive)

inline fun <reified S : Note.Style> NoteUseCase.addSpan(start: Int, end: Int, style: S) =
    addSpan(start, end, style, S::class)

inline fun <reified S : Note.Style> NoteUseCase.addSpan(range: IntRange, style: S) =
    addSpan<S>(range.start, range.endInclusive, style)

fun NoteUseCase.clearSpans(range: IntRange) = clearSpans(range.start, range.endInclusive)
