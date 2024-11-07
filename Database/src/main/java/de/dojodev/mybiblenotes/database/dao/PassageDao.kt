package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.bibleData.Passage
import kotlinx.coroutines.flow.Flow

@Dao
interface PassageDao {
    @Query("SELECT * FROM passages WHERE bibleId=:bibleId and chapterId=:chapterId")
    fun getAll(bibleId: String, chapterId: String): Flow<List<Passage>>

    @Query("SELECT count(id) FROM passages WHERE id=:id")
    fun count(id: String): Long

    @Insert
    fun insertPassage(passage: Passage)

    @Update
    fun updatePassage(passage: Passage)

    @Delete
    fun deletePassage(passage: Passage)
}