package ru.eh13.lizanote.notes.ui.mapper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.notes.ui.model.uiModel.PreviewNoteUiModel
import kotlin.test.Test
import kotlin.test.assertEquals

class NoteToPreviewNoteUiModelMapperTest {
    @Test
    fun test() {
        val notes = listOf(
            Note(
                id = 1,
                name = "1",
                text = "bold italic under strike selected colored",
                spans = mapOf(
                    0 to setOf(Note.Style.Bold),
                    1 to setOf(Note.Style.Bold),
                    2 to setOf(Note.Style.Bold),
                    3 to setOf(Note.Style.Bold),
                    5 to setOf(Note.Style.Italic),
                    6 to setOf(Note.Style.Italic),
                    7 to setOf(Note.Style.Italic),
                    8 to setOf(Note.Style.Italic),
                    9 to setOf(Note.Style.Italic),
                    10 to setOf(Note.Style.Italic),
                    12 to setOf(Note.Style.Underline),
                    13 to setOf(Note.Style.Underline),
                    14 to setOf(Note.Style.Underline),
                    15 to setOf(Note.Style.Underline),
                    16 to setOf(Note.Style.Underline),
                    18 to setOf(Note.Style.Strikethrough),
                    19 to setOf(Note.Style.Strikethrough),
                    20 to setOf(Note.Style.Strikethrough),
                    21 to setOf(Note.Style.Strikethrough),
                    22 to setOf(Note.Style.Strikethrough),
                    23 to setOf(Note.Style.Strikethrough),
                    25 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    26 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    27 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    28 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    29 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    30 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    31 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    32 to setOf(Note.Style.Selected(Note.Color(0xFFa5e3f9))),
                    34 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                    35 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                    36 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                    37 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                    38 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                    39 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                    40 to setOf(Note.Style.Colored(Note.Color(0xFF6b4d8f))),
                )
            ),
            Note(
                id = 2,
                name = "2",
                text = "222"
            ),
            Note(
                id = 3,
                name = "3",
                text = "333"
            ),
        )
        val previewNotes = listOf(
            PreviewNoteUiModel(
                id = 1,
                name = "1",
                text = buildAnnotatedString {
                    append("bold italic under strike selected colored")
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), 0, 1)
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), 1, 2)
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), 2, 3)
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), 3, 4)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 5, 6)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 6, 7)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 7, 8)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 8, 9)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 9, 10)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 10, 11)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 12, 13)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 13, 14)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 14, 15)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 15, 16)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 16, 17)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 18, 19)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 19, 20)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 20, 21)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 21, 22)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 22, 23)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 23, 24)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),25, 26)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),26, 27)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),27, 28)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),28, 29)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),29, 30)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),30, 31)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),31, 32)
                    addStyle(SpanStyle(background = Color(0xFFa5e3f9)),32, 33)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 34, 35)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 35, 36)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 36, 37)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 37, 38)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 38, 39)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 39, 40)
                    addStyle(SpanStyle(color = Color(0xFF6b4d8f)), 40, 41)
                }
            ),
            PreviewNoteUiModel(
                id = 2,
                name = "2",
                text = AnnotatedString("222")
            ),
            PreviewNoteUiModel(
                id = 3,
                name = "3",
                text = AnnotatedString("333")
            )
        )

        val mapper = NoteToPreviewNoteUiModelMapper(NoteToAnnotatedStringMapperImpl())

        val result = notes.map { mapper.map(it) }
        assertEquals(previewNotes, result)
    }
}