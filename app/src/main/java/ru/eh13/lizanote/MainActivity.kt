package ru.eh13.lizanote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import ru.eh13.lizanote.di.MainActivityComponent
import ru.eh13.lizanote.di.MainActivityComponentImpl
import ru.eh13.lizanote.feature.note.flow.noteFlow
import ru.eh13.lizanote.feature.notes.flow.notesFlow
import ru.eh13.lizanote.flow.Flows
import ru.eh13.lizanote.navigation.RouterImpl
import ru.eh13.lizanote.uikit.LizaNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LizaNoteTheme {
                val navController = rememberNavController()
                DisposableEffect(Unit) {
                    RouterImpl.setNavController(navController)
                    onDispose {
                        RouterImpl.setNavController(null)
                    }
                }
                viewModel { MainActivityComponentImpl() }
                NavHost(navController = navController, startDestination = Flows.Notes.name) {
                    notesFlow()
                    noteFlow()
                }
            }
        }
    }
}