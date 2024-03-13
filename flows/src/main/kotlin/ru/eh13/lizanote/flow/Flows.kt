package ru.eh13.lizanote.flow

import ru.eh13.lizanote.navigation.Flow

sealed class Flows : Flow {
    object Notes : Flows() {
        val Notes = "NotesScreen"
    }

    object Note : Flows() {
        val Note = "NoteScreen"
    }
}

