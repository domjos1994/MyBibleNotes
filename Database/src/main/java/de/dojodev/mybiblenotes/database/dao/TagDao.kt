package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.noteData.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    @Query("SELECT * FROM tags")
    fun getAll(): Flow<List<Tag>>

    @Insert
    fun insertTag(tag: Tag): Long

    @Update
    fun updateTag(tag: Tag)

    @Delete
    fun deleteTag(tag: Tag)
}