package ru.eh13.lizanote.feature.notes.flow

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.eh13.lizanote.feature.notes.di.NotesFeatureComponentImpl
import ru.eh13.lizanote.flow.Flows
import ru.eh13.lizanote.navigation.navigation
import ru.eh13.lizanote.notes.ui.view.NotesScreen

fun NavGraphBuilder.notesFlow() {
    navigation(
        route = Flows.Notes,
        startDestination = Flows.Notes.Notes
    ) {
        composable(Flows.Notes.Notes) {
            viewModel { NotesFeatureComponentImpl() }
            NotesScreen()
        }
    }
}