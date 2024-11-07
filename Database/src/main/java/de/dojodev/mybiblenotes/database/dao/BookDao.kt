package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.bibleData.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books WHERE bibleId=:bibleId")
    fun getAll(bibleId: String): Flow<List<Book>>

    @Query("SELECT count(id) FROM books WHERE id=:id")
    fun count(id: String): Long

    @Insert
    fun insertBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)
}