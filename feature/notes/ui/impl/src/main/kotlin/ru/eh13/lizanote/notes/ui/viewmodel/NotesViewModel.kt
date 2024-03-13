package ru.eh13.lizanote.notes.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.eh13.core.viewmodel.annotations.ViewModel
import ru.eh13.lizanote.core.viewmodel.BaseViewModel
import ru.eh13.lizanote.core.viewmodel.UiState
import ru.eh13.lizanote.feature.note.domain.NotesUseCase
import ru.eh13.lizanote.flow.Flows
import ru.eh13.lizanote.navigation.Router
import ru.eh13.lizanote.notes.ui.mapper.NoteToPreviewNoteUiModelMapper
import ru.eh13.lizanote.notes.ui.model.event.NotesUiEvent
import ru.eh13.lizanote.notes.ui.model.uiModel.PreviewNoteUiModel

@ViewModel(NotesUiEvent::class)
class NotesViewModel(
    notesUseCase: NotesUseCase,
    mapper: NoteToPreviewNoteUiModelMapper,
    router: Router
) : BaseViewModel<NotesUiEvent>(router) {
    private val _uiState = MutableNotesUiState()
    val uiState = _uiState as NotesUiState

    init {
        viewModelScope.launch {
            notesUseCase.observeNotes()
                .collect { _uiState.notes = it.map(mapper::map) }
        }
    }

    override fun dispatchEvent(event: NotesUiEvent) {
        when (event) {
            is NotesUiEvent.NoteClicked -> router.navigateToFlow(Flows.Note, event.id)
            NotesUiEvent.AddNoteClicked -> router.navigateToFlow(Flows.Note, null)
        }
    }
}

@Stable
interface NotesUiState : UiState {
    val notes: List<PreviewNoteUiModel>
}

private class MutableNotesUiState : NotesUiState {
    override var notes by mutableStateOf<List<PreviewNoteUiModel>>(emptyList())
}

