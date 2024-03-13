package ru.eh13.lizanote.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.eh13.lizanote.navigation.Router

abstract class BaseViewModel<Event : UiEvent>(protected val router: Router) : ViewModel() {

    final fun sendEvent(event: CommonUiEvent) = dispatchEvent(event)

    final fun sendEvent(event: Event) = dispatchEvent(event)

    @JvmName("dispatchEvent1")
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
            CommonUiEvent.BackClicked -> back()
        }
    }

    abstract protected fun dispatchEvent(event: Event)

    open protected fun back() = router.back()
}

fun BaseViewModel<*>.onBackClicked() = sendEvent(CommonUiEvent.BackClicked)