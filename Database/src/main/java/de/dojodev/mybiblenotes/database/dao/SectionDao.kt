package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.bibleData.Section
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {
    @Query("SELECT * FROM sections WHERE bibleId=:bibleId AND bookId=:bookId AND chapterId=:chapterId")
    fun getAll(bibleId: String, bookId: String, chapterId: String): Flow<List<Section>>

    @Insert
    fun insertSection(section: Section)

    @Update
    fun updateSection(section: Section)

    @Delete
    fun deleteSection(section: Section)
}