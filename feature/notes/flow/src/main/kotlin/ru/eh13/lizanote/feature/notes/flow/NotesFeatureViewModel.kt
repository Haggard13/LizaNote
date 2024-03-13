package ru.eh13.lizanote.feature.notes.flow

import androidx.lifecycle.ViewModel
import ru.eh13.lizanote.flow.Flows
import ru.eh13.lizanote.navigation.Router

class NotesFeatureViewModel internal constructor(private val router: Router) : ViewModel() {
    init {
        router.navigateTo(Flows.Notes.Notes)
    }
}