package ru.eh13.lizanote.ui.mapper

import androidx.compose.ui.graphics.Color
import ru.eh13.lizanote.feature.note.domain.entity.Note
import kotlin.math.pow

fun Color.toNoteColor(): Note.Color {
    val rgb = (alpha * 255) * (16.0.pow(6)) +
        (red * 255) * (16.0.pow(4)) +
        (green * 255) * (16.0.pow(2)) +
        (blue * 255)

    return Note.Color(rgb.toLong())
}