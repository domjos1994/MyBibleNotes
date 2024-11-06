package de.dojodev.mybiblenotes.bibleapi.request

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.dojodev.mybiblenotes.bibleapi.requests.AudioBibleRequest
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
class AudioBibleRequestTest {

    companion object {
        private lateinit var request: AudioBibleRequest
        private lateinit var id: String

        @JvmStatic
        @BeforeClass
        fun init() {
            request = AudioBibleRequest()

            runBlocking {
                id = request.getBibles().first()[0].id
            }
        }
    }

    @Test
    fun getAudioBiblesTest() {
        runBlocking {
            // basic request with default parameters
            val bibles = request.getBibles()
            assertNotEquals(0, bibles.first().size)
            assertEquals(ResponseCode.Successful, request.getState())
            assertEquals("", request.getMessage())
        }
    }

    @Test
    fun getAudioBibleTest() {
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
}