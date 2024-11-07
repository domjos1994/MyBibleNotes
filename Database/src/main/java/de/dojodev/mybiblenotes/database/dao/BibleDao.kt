package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.converter.MediaTypeConverter
import de.dojodev.mybiblenotes.database.model.bibleData.Bible
import kotlinx.coroutines.flow.Flow

@Dao
interface BibleDao {

    @Query("SELECT * FROM bibles")
    fun getAll(): Flow<List<Bible>>

    @Query("SELECT * FROM bibles WHERE mediaType='${MediaTypeConverter.BOOK}'")
    fun getAllBooks(): Flow<List<Bible>>

    @Query("SELECT * FROM bibles WHERE mediaType='${MediaTypeConverter.AUDIO_BOOK}'")
    fun getAllAudioBooks(): Flow<List<Bible>>

    @Insert
    fun insertBible(bible: Bible)

    @Update
    fun updateBible(bible: Bible)

    @Delete
    fun deleteBible(bible: Bible)
}