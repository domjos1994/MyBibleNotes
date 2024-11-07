package de.dojodev.mybiblenotes.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.database.DatabaseTest
import de.dojodev.mybiblenotes.database.model.noteData.Tag
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
class TagDaoTest : DatabaseTest() {
    private lateinit var tagDao: TagDao

    @Before
    override fun init() {
        super.createDb()
        this.tagDao = super.db.tagDao()
    }

    @After
    override fun close() {
        super.closeDB()
    }

    @Test
    fun getTags() {
        runBlocking {
            val tags = tagDao.getAll().first()
            assertNotNull(tags)
        }
    }

    @Test
    fun insertTag() {
        runBlocking {
            val tag = insertItem(tagDao)
            tagDao.deleteTag(tag)
            assertEquals(0, tagDao.getAll().first().size)
        }
    }

    @Test
    fun updateTag() {
        runBlocking {
            val tag = insertItem(tagDao)
            tag.title = "Test-1"
            tagDao.updateTag(tag)
            tagDao.deleteTag(tag)
            assertEquals(0, tagDao.getAll().first().size)
        }
    }

    companion object {

        suspend fun insertItem(tagDao: TagDao): Tag {
            val count = tagDao.getAll().first().size
            val tag = Tag(title = "test")
            tag.id = tagDao.insertTag(tag)
            assertNotEquals(count, tagDao.getAll().first().size)
            return tag
        }
    }
}