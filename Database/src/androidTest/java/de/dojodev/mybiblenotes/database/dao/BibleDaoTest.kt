package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.bibleData.Bible
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
class BibleDaoTest : DatabaseTest() {
    private lateinit var bibleDao: BibleDao

    @Before
    override fun init() {
        super.createDb()
        this.bibleDao = super.db.bibleDao()
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getBibles() {
        runBlocking {
            val bibles = bibleDao.getAll().first()
            assertNotNull(bibles)

            val bookBibles = bibleDao.getAllBooks().first()
            val audioBibles = bibleDao.getAllAudioBooks().first()
            assertEquals(bibles.size, bookBibles.size + audioBibles.size)
        }
    }

    @Test
    fun insertBible() {
        runBlocking {
            val bible = insertItem(bibleDao)
            bibleDao.deleteBible(bible)
            assertEquals(0, bibleDao.getAll().first().size)
        }
    }

    @Test
    fun updateBible() {
        runBlocking {
            val bible = insertItem(bibleDao)
            bible.name = "Test2"
            bibleDao.updateBible(bible)
            bibleDao.deleteBible(bible)
            assertEquals(0, bibleDao.getAll().first().size)
        }
    }

    companion object {

        suspend fun insertItem(bibleDao: BibleDao): Bible {
            val count = bibleDao.getAll().first().size
            val bible = Bible("1", name = "Test-Bible")
            bibleDao.insertBible(bible)
            assertNotEquals(count, bibleDao.getAll().first().size)
            return bible
        }
    }
}