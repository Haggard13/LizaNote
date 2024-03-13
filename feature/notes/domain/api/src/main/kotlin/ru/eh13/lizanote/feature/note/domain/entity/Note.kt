package ru.eh13.lizanote.feature.note.domain.entity

import kotlin.reflect.KClass

data class Note(
    val id: Long? = null,
    val name: String = "",
    val text: String = "",
    val spans: Map<Int, Set<Style>> = emptyMap(),
) {

    fun withText(newText: String): Note {
        val newSpans = spans.filter { it.key < newText.length }
        return copy(
            text = newText,
            spans = newSpans.toMap()
        )
    }

    fun <S : Style> withoutStyleInRange(start: Int, end: Int, style: KClass<S>): Note {
        val range = start..end
        val newSpans = spans
            .mapValues { (index, styles) ->
                styles.filterNot { index in range && style.isInstance(it) }.toSet()
            }

        return copy(spans = newSpans.toMap())
    }

    fun <S : Style> withStyleInRange(start: Int, end: Int, style: S, styleClass: KClass<S>): Note {
        require(start <= text.length)
        require(end <= text.length)
        val range = start .. end

        val newSpans = spans.toMutableMap()
        range.forEach {
            val styles = newSpans[it]
            if (styles == null) {
                newSpans[it] = setOf(style)
            } else {
                newSpans[it] = (styles.filterNot { styleClass.isInstance(it) } + style).toSet()
            }
        }
        return copy(spans = newSpans.toMap())
    }

    fun withoutSpansInRange(start: Int, end: Int): Note {
        val range = start..end
        val newSpans = spans.filterNot { it.key in range }

        return copy(spans = newSpans.toMap())
    }

    sealed interface Style {
        data object Bold : Style
        data object Italic : Style
        data object Underline : Style
        data object Strikethrough : Style
        data class Selected(val color: Color) : Style
        data class Colored(val color: Color) : Style
    }

    @OptIn(ExperimentalStdlibApi::class)
    class Color(val hex: Long) {

        override fun equals(other: Any?) = (other as? Color)?.hex == hex

        override fun hashCode() = hex.hashCode()

        override fun toString() = hex.toHexString()
    }

    companion object
}
