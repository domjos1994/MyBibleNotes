package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.noteData.NoteType
import de.dojodev.mybiblenotes.database.model.noteData.Relation
import de.dojodev.mybiblenotes.database.model.noteData.RelationType
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
class RelationDaoTest : DatabaseTest() {
    private lateinit var relationDao: RelationDao
    private lateinit var bookId: String
    private var noteId: Long = 0L

    @Before
    override fun init() {
        super.createDb()
        this.relationDao = super.db.relationDao()

        runBlocking {
            val bibleId = BibleDaoTest.insertItem(super.db.bibleDao()).id
            bookId = BookDaoTest.insertItem(super.db.bookDao(), bibleId).id
            noteId = NoteDaoTest.insertItem(super.db.noteDao()).id
        }
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getRelations() {
        runBlocking {
            val relations = relationDao.getAll().first()
            assertNotNull(relations)
        }
    }

    @Test
    fun insertRelation() {
        runBlocking {
            val relation = insertItem(relationDao, bookId, noteId)
            relationDao.deleteRelation(relation)
            assertEquals(0, relationDao.getRelationByType(bookId, RelationType.Book).first().size)
        }
    }

    @Test
    fun updateRelation() {
        runBlocking {
            val relation = insertItem(relationDao, bookId, noteId)
            relation.title = "Test-1"
            relationDao.updateRelation(relation)
            relationDao.deleteRelation(relation)
            assertEquals(0, relationDao.getAll().first().size)
        }
    }

    companion object {

        suspend fun insertItem(relationDao: RelationDao, bookId: String, noteId: Long): Relation {
            val count = relationDao.getAll().first().size
            val relation = Relation(
                foreignId = bookId,
                foreignType = RelationType.Book,
                noteId = "$noteId",
                noteType = NoteType.Note,
                title = "Test"
            )
            relation.id = relationDao.insertRelation(relation)
            assertNotEquals(count, relationDao.getAll().first().size)
            return relation
        }
    }
}