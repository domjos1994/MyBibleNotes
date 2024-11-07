package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.bibleData.Book
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookDaoTest : DatabaseTest() {
    private lateinit var bookDao: BookDao
    private lateinit var bibleId: String

    @Before
    override fun init() {
        super.createDb()
        this.bookDao = super.db.bookDao()
        runBlocking {
            bibleId = BibleDaoTest.insertItem(super.db.bibleDao()).id
        }
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getBooks() {
        runBlocking {
            val bibles = bookDao.getAll(bibleId).first()
            assertNotNull(bibles)
        }
    }

    @Test
    fun insertBook() {
        runBlocking {
            val bible = insertItem(bookDao, bibleId)
            bookDao.deleteBook(bible)
            assertEquals(0, bookDao.getAll(bibleId).first().size)
        }
    }

    @Test
    fun updateBook() {
        runBlocking {
            val bible = insertItem(bookDao, bibleId)
            bible.name = "Test2"
            bookDao.updateBook(bible)
            bookDao.deleteBook(bible)
            assertEquals(0, bookDao.getAll(bibleId).first().size)
        }
    }

    companion object {

        suspend fun insertItem(bookDao: BookDao, id: String): Book {
            val count = bookDao.getAll(id).first().size
            val book = Book("1", id, name = "Marcus")
            bookDao.insertBook(book)
            assertNotEquals(count, bookDao.getAll(id).first().size)
            return book
        }
    }
}