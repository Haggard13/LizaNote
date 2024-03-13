package ru.eh13.lizanote.feature.note.data.mapper

import org.junit.Test
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.feature.note.domain.entity.Note.Style.*
import kotlin.test.assertEquals

class SpansSerializerToStringTest {
    @Test
    fun `when call toString then return string`() {
        val spans = mapOf(
            1 to setOf(Bold, Italic),
            2 to setOf(Underline, Bold),
            10 to setOf(Underline, Colored(Note.Color(0xFFFFFFFF))),
            11 to setOf(Selected(Note.Color(0xFAFAFAFA)))
        )
        val spanParser = SpansSerializer()

        assertEquals(
            "(1:bold,italic,)(2:underline,bold,)(10:underline,colored<4294967295>,)(11:selected<4210752250>,)",
            spanParser.serialize(spans)
        )
    }
}