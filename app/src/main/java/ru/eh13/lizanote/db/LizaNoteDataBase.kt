package ru.eh13.lizanote.db

import android.content.Context
import androidx.room.*
import ru.eh13.lizanote.feature.note.data.api.NoteDao
import ru.eh13.lizanote.feature.note.data.model.NoteDto

@Database(version = 1, entities = [NoteDto::class])
abstract class LizaNoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: LizaNoteDataBase? = null

        fun getInstance(context: Context? = null): LizaNoteDataBase {
            var instance = instance
            return if (instance != null) {
                instance
            } else {
                if (context == null) error("Data Base is not created yet. You should put Context as argument")
                instance = Room.databaseBuilder(
                    context,
                    LizaNoteDataBase::class.java,
                    "LizaNoteDataBase"
                ).build()
                this.instance = instance
                instance
            }
        }
    }
}