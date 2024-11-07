package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.bibleData.Section
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
class SectionDaoTest : DatabaseTest() {
    private lateinit var sectionDao: SectionDao
    private lateinit var bibleId: String
    private lateinit var bookId: String
    private lateinit var chapterId: String

    @Before
    override fun init() {
        super.createDb()
        this.sectionDao = super.db.sectionDao()
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
    fun getPassages() {
        runBlocking {
            val bibles = sectionDao.getAll(bibleId, bookId, chapterId).first()
            assertNotNull(bibles)
        }
    }

    @Test
    fun insertPassage() {
        runBlocking {
            val passage = insertItem(sectionDao, bibleId, bookId, chapterId)
            sectionDao.deleteSection(passage)
            assertEquals(0, sectionDao.getAll(bibleId, bookId, chapterId).first().size)
        }
    }

    @Test
    fun updatePassage() {
        runBlocking {
            val chapter = insertItem(sectionDao, bibleId, bookId, chapterId)
            chapter.content = "Test2"
            sectionDao.updateSection(chapter)
            sectionDao.deleteSection(chapter)
            assertEquals(0, sectionDao.getAll(bibleId, bookId, chapterId).first().size)
        }
    }

    companion object {

        suspend fun insertItem(sectionDao: SectionDao, id: String, bookId: String, chapterId: String): Section {
            val count = sectionDao.getAll(id, bookId, chapterId).first().size
            val section = Section("1", id, bookId, chapterId, "Test")
            sectionDao.insertSection(section)
            assertNotEquals(count, sectionDao.getAll(id, bookId, chapterId).first().size)
            return section
        }
    }
}