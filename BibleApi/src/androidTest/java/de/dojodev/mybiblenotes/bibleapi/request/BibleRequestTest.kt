package de.dojodev.mybiblenotes.bibleapi.request

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.bibleapi.requests.BibleRequest
import de.dojodev.mybiblenotes.bibleapi.requests.base.ResponseCode
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BibleRequestTest {

    companion object {
        private lateinit var request: BibleRequest
        private lateinit var id: String

        @JvmStatic
        @BeforeClass
        fun init() {
            request = BibleRequest()
            runBlocking {
                id = request.getBibles().first()[1].id
            }
        }
    }

    @Test
    fun getBiblesTest() {
        runBlocking {
            // basic request with default parameters
            val bibles = request.getBibles()
            assertNotEquals(0, bibles.first().size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())
        }
    }

    @Test
    fun getBibleTest() {
        runBlocking {
            val bible = request.getBible(id).first()
            assertNotNull(bible)
            assertEquals(id, bible?.id ?: "")
        }
    }

    @Test
    fun getBooksTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())
        }
    }

    @Test
    fun getBookTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[0].id
            assertNotNull(bookId)
            val book = request.getBook(id, bookId).first()
            assertNotNull(book)
        }
    }

    @Test
    fun getChaptersTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[0].id
            assertNotNull(bookId)
            val chapters = request.getChapters(id, bookId).first()
            assertNotEquals(0, chapters.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

        }
    }

    @Test
    fun getChapterTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[0].id
            assertNotNull(bookId)
            val chapters = request.getChapters(id, bookId).first()
            assertNotEquals(0, chapters.size)
            val chapterId = chapters[0].id
            val chapter = request.getChapter(id, chapterId).first()
            assertNotNull(chapter)
        }
    }

    @Test
    fun getSectionsInBookTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[1].id
            assertNotNull(bookId)
            val sections = request.getSectionsInBook(id, bookId).first()
            assertNotEquals(0, sections.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())
        }
    }

    @Test
    fun getSectionsInChapterTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[1].id
            assertNotNull(bookId)
            val chapters = request.getChapters(id, bookId).first()
            assertNotEquals(0, chapters.size)
            val chapterId = chapters[1].id
            val sections = request.getSectionsInChapter(id, chapterId).first()
            assertNotEquals(0, sections.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())
        }
    }

    @Test
    fun getSectionTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[0].id
            assertNotNull(bookId)
            val sections = request.getSectionsInBook(id, bookId).first()
            assertNotEquals(0, sections.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            val sectionId = sections[0].id
            val section = request.getSection(id, sectionId).first()
            assertNotNull(section)
        }
    }

    @Test
    fun getPassageTest() {
        runBlocking {
            val books = request.getBooks(id).first()
            assertNotEquals(0, books.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            // get book
            val bookId = books[0].id
            assertNotNull(bookId)
            val chapters = request.getChapters(id, bookId).first()
            assertNotEquals(0, chapters.size)
            val chapterId = chapters[1].id

            val passage = request.getPassage(id, chapterId).first()
            assertNotNull(passage)
        }
    }

    @Test
    fun getVersesTest() {
        runBlocking {
            val verses = request.getVerses("f492a38d0e52db0f-01", "GEN.2").first()
            assertNotEquals(0, verses.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())
        }
    }

    @Test
    fun getVerseTest() {
        runBlocking {
            val verses = request.getVerses("f492a38d0e52db0f-01", "GEN.2").first()
            assertNotEquals(0, verses.size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())

            val verseId = verses[1].id
            val verse = request.getVerse(id, verseId).first()
            assertNotNull(verse)
        }
    }

    @Test
    fun searchTest() {
        runBlocking {
            val response = request.search(id, "Matthew").first()
            assertNotNull(response)
            assertNotEquals(-1, response?.verseCount)
        }
    }
}