package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.bibleData.Passage
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
class PassageDaoTest : DatabaseTest() {
    private lateinit var passageDao: PassageDao
    private lateinit var bibleId: String
    private lateinit var bookId: String

    @Before
    override fun init() {
        super.createDb()
        this.passageDao = super.db.passageDao()
        runBlocking {
            bibleId = BibleDaoTest.insertItem(super.db.bibleDao()).id
            bookId = BookDaoTest.insertItem(super.db.bookDao(), bibleId).id
        }
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getPassages() {
        runBlocking {
            val bibles = passageDao.getAll(bibleId, bookId).first()
            assertNotNull(bibles)
        }
    }

    @Test
    fun insertPassage() {
        runBlocking {
            val passage = insertItem(passageDao, bibleId, bookId)
            passageDao.deletePassage(passage)
            assertEquals(0, passageDao.getAll(bibleId, bookId).first().size)
        }
    }

    @Test
    fun updatePassage() {
        runBlocking {
            val chapter = insertItem(passageDao, bibleId, bookId)
            chapter.content = "Test2"
            passageDao.updatePassage(chapter)
            passageDao.deletePassage(chapter)
            assertEquals(0, passageDao.getAll(bibleId, bookId).first().size)
        }
    }

    companion object {

        suspend fun insertItem(passageDao: PassageDao, id: String, bookId: String): Passage {
            val count = passageDao.getAll(id, bookId).first().size
            val passage = Passage("1", id, bookId, "42.1")
            passageDao.insertPassage(passage)
            assertNotEquals(count, passageDao.getAll(id, bookId).first().size)
            return passage
        }
    }
}