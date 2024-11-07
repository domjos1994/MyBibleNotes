package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.bibleData.Verse
import kotlinx.coroutines.flow.Flow

@Dao
interface VerseDao {
    @Query("SELECT * FROM verses WHERE bibleId=:bibleId AND bookId=:bookId and chapterId=:chapterId")
    fun getAll(bibleId: String, bookId: String, chapterId: String): Flow<List<Verse>>

    @Query("SELECT count(id) FROM verses WHERE id=:id")
    fun count(id: String): Long

    @Insert
    fun insertVerse(verse: Verse)

    @Update
    fun updateVerse(verse: Verse)

    @Delete
    fun deleteVerse(verse: Verse)
}