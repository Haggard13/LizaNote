package ru.eh13.lizanote.feature.note.data.mapper

import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import kotlin.test.Test
import kotlin.test.assertFailsWith

@RunWith(Parameterized::class)
class SpansDeserializerFromStringPissimisticTest(
    private val invalidString: String
) {

    @Test
    fun `when send invalide string then throw IllegalArgumentException`() {
        val spanParser = SpansDeserializer()

        assertFailsWith(exceptionClass = IllegalArgumentException::class) { spanParser.deserialize(invalidString) }
    }

    companion object {
        @JvmStatic
        @Parameters
        fun invalidString() = listOf(
            arrayOf("invalid String"),
            arrayOf("(:bold,)"),
            arrayOf("(1:bold)"),
            arrayOf("(1:colored<>,)"),
            arrayOf("1:bold,"),
            arrayOf("(1:colored<FFFFFFFF>,)"),
            arrayOf("(1:colored<123FFF>,)"),
            arrayOf("(1:bold,)("),
        )
    }
}