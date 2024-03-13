package ru.eh13.lizanote.feature.note.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteDto(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val text: String,
    val spans: String,
)