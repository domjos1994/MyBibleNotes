package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.noteData.NotePart
import kotlinx.coroutines.flow.Flow

@Dao
interface NotePartDao {

    @Query("SELECT * FROM noteParts")
    fun getAll(): Flow<List<NotePart>>

    @Query("SELECT * FROM noteParts WHERE noteId=:noteId")
    fun getNotePartsOfNote(noteId: Long): Flow<List<NotePart>>

    @Insert
    fun insertNotePart(notePart: NotePart): Long

    @Update
    fun updateNotePart(notePart: NotePart)

    @Delete
    fun deleteNotePart(notePart: NotePart)
}
