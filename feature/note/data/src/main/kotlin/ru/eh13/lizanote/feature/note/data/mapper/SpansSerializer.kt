package ru.eh13.lizanote.feature.note.data.mapper

import ru.eh13.lizanote.feature.note.data.model.StyleDto
import ru.eh13.lizanote.feature.note.domain.entity.Note

internal class SpansSerializer {

    fun serialize(spans: Map<Int, Set<Note.Style>>): String {
        return spans.map { (index, styles) -> "($index$INDEX_DELIMITER${styles.serialize()})" }.joinToString("")
    }

    private fun Set<Note.Style>.serialize(): String {
        return buildString {
            this@serialize.forEach {
                append(getDtoFromStyle(it).code)
                append(serializeParameter(it))
                append(STYLES_DELIMITER)
            }
        }
    }

    private fun getDtoFromStyle(style: Note.Style): StyleDto {
        return when (style) {
            Note.Style.Bold -> StyleDto.BOLD
            Note.Style.Italic -> StyleDto.ITALIC
            Note.Style.Underline -> StyleDto.UNDERLINE
            Note.Style.Strikethrough -> StyleDto.STRIKETHROUGH
            is Note.Style.Colored -> StyleDto.COLORED
            is Note.Style.Selected -> StyleDto.SELECTED
        }
    }

    private fun serializeParameter(style: Note.Style): String {
        return when (style) {
            is Note.Style.Colored -> "$PARAMETER_OPEN${style.color.hex}$PARAMETER_CLOSE"
            is Note.Style.Selected -> "$PARAMETER_OPEN${style.color.hex}$PARAMETER_CLOSE"
            else -> ""
        }
    }

    internal companion object {
        //"(1:bold,italic,color,underline,color<ff00ff00>,selected<ff00ff00>,)"
        val INDEX_DELIMITER = ":"
        val STYLES_DELIMITER = ","
        val PARAMETER_OPEN = "<"
        val PARAMETER_CLOSE = ">"
    }
}