package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.noteData.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<Note>>

    @Insert
    fun insertNote(note: Note): Long

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}