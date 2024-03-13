package ru.eh13.lizanote.feature.note.data.mapper

import ru.eh13.lizanote.feature.note.data.model.StyleDto
import ru.eh13.lizanote.feature.note.domain.entity.Note

internal class SpansDeserializer {
    fun deserialize(spans: String): Map<Int, Set<Note.Style>> {
        require(SPANS_REGEX matches spans) { "Неверный формат строки: $spans" }
        val result = mutableMapOf<Int, Set<Note.Style>>()
        SPAN_REGEX.findAll(spans).forEach {
            val (index, styles) = it.value.removeSurrounding("(", ")").split(SpansSerializer.INDEX_DELIMITER)
            result[index.toInt()] = getStylesFromString(styles)
        }

        return result
    }

    private fun getStylesFromString(styles: String): Set<Note.Style> {
        return styles.splitToSequence(SpansSerializer.STYLES_DELIMITER)
            .mapNotNull { style -> getStyleFromString(style) }
            .toSet()
    }

    private fun getStyleFromString(style: String): Note.Style? {
        return when (StyleDto.getByStyle(style)) {
            StyleDto.BOLD -> Note.Style.Bold
            StyleDto.ITALIC -> Note.Style.Italic
            StyleDto.UNDERLINE -> Note.Style.Underline
            StyleDto.STRIKETHROUGH -> Note.Style.Strikethrough
            StyleDto.COLORED -> Note.Style.Colored(Note.Color(getStyleParameter(style).toLong()))
            StyleDto.SELECTED -> Note.Style.Selected(Note.Color(getStyleParameter(style).toLong()))
            else -> null
        }
    }

    private fun getStyleParameter(style: String) =
        style.substringAfter(SpansSerializer.PARAMETER_OPEN).removeSuffix(SpansSerializer.PARAMETER_CLOSE)

    private companion object {
        private val SPAN_REGEX = Regex("\\((\\d)+:(([a-z])+(<\\d+>)?,)+\\)")
        private val SPANS_REGEX = Regex("(${SPAN_REGEX.pattern})*")
    }
}