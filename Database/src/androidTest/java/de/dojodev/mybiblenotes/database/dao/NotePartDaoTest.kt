package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.noteData.NotePart
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class NotePartDaoTest : DatabaseTest() {
    private lateinit var notePartDao: NotePartDao
    private var noteId: Long = 0L

    @Before
    override fun init() {
        super.createDb()
        this.notePartDao = super.db.notePartDao()

        runBlocking {
            noteId = NoteDaoTest.insertItem(super.db.noteDao()).id
        }
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getNoteParts() {
        runBlocking {
            val noteParts = notePartDao.getAll().first()
            assertNotNull(noteParts)
            assertEquals(noteParts.size, notePartDao.getNotePartsOfNote(noteId).first().size)
        }
    }

    @Test
    fun insertNoteParts() {
        runBlocking {
            val notePart = insertItem(notePartDao, noteId)
            notePartDao.deleteNotePart(notePart)
            assertEquals(0, notePartDao.getAll().first().size)
        }
    }

    @Test
    fun updateNoteParts() {
        runBlocking {
            val notePart = insertItem(notePartDao, noteId)
            notePart.title = "Test-1"
            notePartDao.updateNotePart(notePart)
            notePartDao.deleteNotePart(notePart)
            assertEquals(0, notePartDao.getAll().first().size)
        }
    }

    companion object {

        suspend fun insertItem(notePartDao: NotePartDao, noteId: Long): NotePart {
            val count = notePartDao.getAll().first().size
            val notePart = NotePart(title = "test", noteId = noteId, insertDate = Date())
            notePart.id = notePartDao.insertNotePart(notePart)
            assertNotEquals(count, notePartDao.getAll().first().size)
            return notePart
        }
    }
}