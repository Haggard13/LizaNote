package ru.eh13.lizanote.notes.ui.model.event

import ru.eh13.lizanote.core.viewmodel.UiEvent

sealed interface NotesUiEvent : UiEvent {
    class NoteClicked(val id: Long) : NotesUiEvent
    object AddNoteClicked : NotesUiEvent
}