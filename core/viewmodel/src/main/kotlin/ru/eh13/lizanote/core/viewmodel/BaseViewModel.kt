package ru.eh13.lizanote.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.eh13.lizanote.navigation.Router

abstract class BaseViewModel<Event : UiEvent, State : UiState>(protected val router: Router) : ViewModel() {

    protected abstract val _uiState: MutableStateFlow<State>
    final val uiState = _uiState.asStateFlow()

    final fun sendEvent(event: CommonUiEvent) = dispatchEvent(event)

    final fun sendEvent(event: Event) = dispatchEvent(event)

    private fun dispatchEvent(event: UiEvent) {
        if (event is CommonUiEvent) {
            dispatchCommonEvent(event)
        } else {
            @Suppress("UNCHECKED_CAST")
            dispatchEvent(event as Event)
        }
    }

    private fun dispatchCommonEvent(event: CommonUiEvent) {
        when (event) {
            CommonUiEvent.BackClicked -> router.back()
        }
    }

    abstract protected fun dispatchEvent(event: Event)

    final fun onBackClicked() = sendEvent(CommonUiEvent.BackClicked)

    open protected fun back() = router.back()
}