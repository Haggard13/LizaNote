package ru.eh13.lizanote.feature.note.data.mapper

import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import ru.eh13.lizanote.feature.note.domain.entity.Note
import ru.eh13.lizanote.feature.note.domain.entity.Note.Style.*
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class SpansDeserializerFromStringOptimisticTest(
    private val string: String,
    private val result: Map<Int, Set<Note.Style>>
) {

    @Test
    fun `when send invalide string then throw IllegalArgumentException`() {
        val spanParser = SpansDeserializer()

        assertEquals(result, spanParser.deserialize(string))
    }

    companion object {
        @JvmStatic
        @Parameters
        fun data() = listOf(
            arrayOf("", mapOf<Int, Set<Note.Style>>()),
            arrayOf("(1:bold,)", mapOf(1 to setOf(Bold))),
            arrayOf("(1:bold,)(2:bold,)(3:bold,)", mapOf(1 to setOf(Bold), 2 to setOf(Bold), 3 to setOf(Bold))),
            arrayOf("(1:bold,)(2:colored<1234235>,)(3:bold,)", mapOf(1 to setOf(Bold), 2 to setOf(Colored(Note.Color(0x12D53B))), 3 to setOf(Bold))),
            arrayOf("(1:bold,)(2:italic,bold,)", mapOf(1 to setOf(Bold), 2 to setOf(Bold, Italic))),
        )
    }
}