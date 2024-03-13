package ru.eh13.lizanote.feature.note.data.api

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.eh13.lizanote.feature.note.data.model.NoteDto

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteDto")
    suspend fun getNotes(): List<NoteDto>

    @Query("SELECT * FROM NoteDto WHERE id = :id")
    suspend fun getNote(id: Long): NoteDto

    @Upsert
    suspend fun save(note: NoteDto)
}