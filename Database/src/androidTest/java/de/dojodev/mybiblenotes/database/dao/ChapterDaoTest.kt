package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.bibleData.Chapter
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
class ChapterDaoTest : DatabaseTest() {
    private lateinit var chapterDao: ChapterDao
    private lateinit var bibleId: String
    private lateinit var bookId: String

    @Before
    override fun init() {
        super.createDb()
        this.chapterDao = super.db.chapterDao()
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
    fun getChapters() {
        runBlocking {
            val bibles = chapterDao.getAll(bibleId, bookId).first()
            assertNotNull(bibles)
        }
    }

    @Test
    fun insertChapter() {
        runBlocking {
            val chapter = insertItem(chapterDao, bibleId, bookId)
            chapterDao.deleteChapter(chapter)
            assertEquals(0, chapterDao.getAll(bibleId, bookId).first().size)
        }
    }

    @Test
    fun updateChapter() {
        runBlocking {
            val chapter = insertItem(chapterDao, bibleId, bookId)
            chapter.number = "Test2"
            chapterDao.updateChapter(chapter)
            chapterDao.deleteChapter(chapter)
            assertEquals(0, chapterDao.getAll(bibleId, bookId).first().size)
        }
    }

    companion object {

        suspend fun insertItem(chapterDao: ChapterDao, id: String, bookId: String): Chapter {
            val count = chapterDao.getAll(id, bookId).first().size
            val chapter = Chapter("1", id, bookId, "42.1")
            chapterDao.insertChapter(chapter)
            assertNotEquals(count, chapterDao.getAll(id, bookId).first().size)
            return chapter
        }
    }
}