package ru.eh13.lizanote.notes.ui.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.feature.notes.ui.mapper.NoteToAnnotatedStringMapper

class NoteToAnnotatedStringMapperImpl : NoteToAnnotatedStringMapper {
    override fun map(note: Note): AnnotatedString {
        return buildAnnotatedString {
            append(note.text)
            note.spans.forEach { (index, styles) ->
                styles.forEach {
                    val spanStyle = when (it) {
                        Note.Style.Bold -> SpanStyle(fontWeight = FontWeight.Bold)
                        Note.Style.Italic -> SpanStyle(fontStyle = FontStyle.Italic)
                        Note.Style.Underline -> SpanStyle(textDecoration = TextDecoration.Underline)
                        Note.Style.Strikethrough -> SpanStyle(textDecoration = TextDecoration.LineThrough)
                        is Note.Style.Colored -> SpanStyle(color = Color(it.color.hex))
                        is Note.Style.Selected -> SpanStyle(background = Color(it.color.hex))
                    }
                    addStyle(
                        style = spanStyle,
                        start = index,
                        end = index + 1
                    )
                }
            }
        }
    }
}