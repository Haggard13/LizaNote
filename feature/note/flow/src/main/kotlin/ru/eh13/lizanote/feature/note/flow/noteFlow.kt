package ru.eh13.lizanote.feature.note.flow

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import ru.eh13.lizanote.feature.note.di.NoteFeatureComponentImpl
import ru.eh13.lizanote.navigation.navigation
import ru.eh13.lizanote.flow.Flows
import ru.eh13.lizanote.navigation.composableWithArgs
import ru.eh13.lizanote.navigation.composableWithFlowArgs
import ru.eh13.lizanote.ui.view.NoteScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.noteFlow() {
    navigation<Long>(
        route = Flows.Note,
        startDestination = Flows.Note.Note,
    ) {
        composableWithFlowArgs<Long>(Flows.Note.Note) {
            viewModel { NoteFeatureComponentImpl() }
            NoteScreen(it)
        }
    }
}