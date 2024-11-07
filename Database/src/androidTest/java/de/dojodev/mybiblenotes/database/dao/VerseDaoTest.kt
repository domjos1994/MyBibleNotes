package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.bibleData.Verse
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
class VerseDaoTest : DatabaseTest() {
    private lateinit var verseDao: VerseDao
    private lateinit var bibleId: String
    private lateinit var bookId: String
    private lateinit var chapterId: String

    @Before
    override fun init() {
        super.createDb()
        this.verseDao = super.db.verseDao()
        runBlocking {
            bibleId = BibleDaoTest.insertItem(super.db.bibleDao()).id
            bookId = BookDaoTest.insertItem(super.db.bookDao(), bibleId).id
            chapterId = ChapterDaoTest.insertItem(super.db.chapterDao(), bibleId, bookId).id
        }
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getVerses() {
        runBlocking {
            val bibles = verseDao.getAll(bibleId, bookId, chapterId).first()
            assertNotNull(bibles)
        }
    }

    @Test
    fun insertVerse() {
        runBlocking {
            val passage = insertItem(verseDao, bibleId, bookId, chapterId)
            verseDao.deleteVerse(passage)
            assertEquals(0, verseDao.getAll(bibleId, bookId, chapterId).first().size)
        }
    }

    @Test
    fun updateVerse() {
        runBlocking {
            val chapter = insertItem(verseDao, bibleId, bookId, chapterId)
            chapter.content = "Test2"
            verseDao.updateVerse(chapter)
            verseDao.deleteVerse(chapter)
            assertEquals(0, verseDao.getAll(bibleId, bookId, chapterId).first().size)
        }
    }

    companion object {

        suspend fun insertItem(verseDao: VerseDao, id: String, bookId: String, chapterId: String): Verse {
            val count = verseDao.getAll(id, bookId, chapterId).first().size
            val verse = Verse("1", "", id, bookId, chapterId, "Test")
            verseDao.insertVerse(verse)
            assertNotEquals(count, verseDao.getAll(id, bookId, chapterId).first().size)
            return verse
        }
    }
}