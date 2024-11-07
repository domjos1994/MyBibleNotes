package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.bibleData.Chapter
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {

    @Query("SELECT * FROM chapters WHERE bibleId=:bibleId AND bookId=:bookId")
    fun getAll(bibleId: String, bookId: String): Flow<List<Chapter>>

    @Query("SELECT count(id) FROM chapters WHERE id=:id")
    fun count(id: String): Long

    @Insert
    fun insertChapter(chapter: Chapter)

    @Update
    fun updateChapter(chapter: Chapter)

    @Delete
    fun deleteChapter(chapter: Chapter)
}