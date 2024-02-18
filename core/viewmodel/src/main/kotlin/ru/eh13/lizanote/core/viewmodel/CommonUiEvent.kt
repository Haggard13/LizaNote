package ru.eh13.lizanote.core.viewmodel

sealed interface CommonUiEvent : UiEvent {
    object BackClicked : CommonUiEvent
}