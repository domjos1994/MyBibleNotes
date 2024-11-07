package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.noteData.Note
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
class NoteDaoTest : DatabaseTest() {
    private lateinit var noteDao: NoteDao

    @Before
    override fun init() {
        super.createDb()
        this.noteDao = super.db.noteDao()
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getNotes() {
        runBlocking {
            val notes = noteDao.getAll().first()
            assertNotNull(notes)
        }
    }

    @Test
    fun insertNote() {
        runBlocking {
            val note = insertItem(noteDao)
            noteDao.deleteNote(note)
            assertEquals(0, noteDao.getAll().first().size)
        }
    }

    @Test
    fun updateNote() {
        runBlocking {
            val note = insertItem(noteDao)
            note.title = "Test-1"
            noteDao.updateNote(note)
            noteDao.deleteNote(note)
            assertEquals(0, noteDao.getAll().first().size)
        }
    }

    companion object {

        suspend fun insertItem(noteDao: NoteDao): Note {
            val count = noteDao.getAll().first().size
            val note = Note(title = "test", insertDate = Date())
            note.id = noteDao.insertNote(note)
            assertNotEquals(count, noteDao.getAll().first().size)
            return note
        }
    }
}