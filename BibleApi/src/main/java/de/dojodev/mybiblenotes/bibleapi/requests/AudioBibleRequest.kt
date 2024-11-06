package de.dojodev.mybiblenotes.bibleapi.requests

import de.dojodev.mybiblenotes.bibleapi.model.bible.AudioBible
import de.dojodev.mybiblenotes.bibleapi.model.bible.AudioBibleSummary
import de.dojodev.mybiblenotes.bibleapi.model.book.Book
import de.dojodev.mybiblenotes.bibleapi.model.chapter.AudioChapter
import de.dojodev.mybiblenotes.bibleapi.model.chapter.ChapterSummary
import de.dojodev.mybiblenotes.bibleapi.requests.base.BasicRequest
import de.dojodev.mybiblenotes.bibleapi.requests.base.ResponseCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import java.util.Locale

/**
 * Call to endpoint audio-bibles
 * @author Dominic Joas
 * @since 1.0
 */
class AudioBibleRequest : BasicRequest("audio-bibles") {

    /**
     * Gets an array of audio Bible objects authorized for current API Key
     * @param language ISO 639-3 three digit language code used to filter results
     * @param abbreviation Bible abbreviation to search for
     * @param name Bible name to search for
     * @param ids Comma separated list of Bible Ids to return
     * @param full Boolean to include full Bible details (e.g. copyright and promo info)
     */
    fun getBibles(
        language: Locale = Locale.getDefault(),
        abbreviation: String = "",
        name: String = "",
        ids: String = "",
        bibleId: String = "",
        full: Boolean = false
    ): Flow<List<AudioBibleSummary>> {

        val paramAbbreviation = if(abbreviation.isEmpty()) "" else "&abbreviation=$abbreviation"
        val paramName = if(name.isEmpty()) "" else "&name=$name"
        val paramIds = if(ids.isEmpty()) "" else "&ids=$ids"
        val paramBibleId = if(ids.isEmpty()) "" else "&bibleId=$bibleId"

        val request = super.buildRequest(
            "?language=${language.isO3Language}$paramAbbreviation$paramName" +
                    "$paramIds$paramBibleId&include-full-details=$full",
            "GET",
            null
        )

        return flow {
            if(request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if(super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<AudioBibleListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }
            }
        }
    }

    /**
     * Gets a single audio Bible for a given audioBibleId
     * @param bibleId Id of audio Bible to be fetched
     */
    fun getBible(
        bibleId: String
    ): Flow<AudioBible?> {

        val request = super.buildRequest("/$bibleId", "GET", null)

        return flow {
            if(request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if(super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<AudioBibleResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }
            }
        }
    }

    /**
     * Gets an array of Book objects for a given audioBibleId
     * @param bibleId Id of audio Bible whose Book to fetch
     * @param chapters Boolean indicating if an array of chapter summaries should be
     * included in the results. Defaults to false.
     */
    fun getBooks(
        bibleId: String,
        chapters: Boolean = false
    ): Flow<List<Book>> {

        val request = super.buildRequest(
            "/$bibleId/books?include-chapters=$chapters",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<BookListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }

            }
        }
    }

    /**
     * Gets a single Book object for a given audioBibleId and bookId
     * @param bibleId Id of audio Bible whose Book to fetch
     * @param bookId Id of the Book to fetch
     * @param chapters Boolean indicating if an array of chapter summaries should be
     * included in the results. Defaults to false.
     */
    fun getBook(
        bibleId: String,
        bookId: String,
        chapters: Boolean = false
    ): Flow<Book?> {

        val request = super.buildRequest(
            "/$bibleId/books/$bookId?include-chapters=$chapters",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<BookResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }

            }
        }
    }

    /**
     * Gets an array of Chapter objects for a given audioBibleId and bookId
     * @param bibleId Id of Bible whose Chapters to fetch
     * @param bookId Id of the Book whose Chapters to fetch
     */
    fun getChapters(
        bibleId: String,
        bookId: String
    ): Flow<List<ChapterSummary>> {

        val request = super.buildRequest(
            "/$bibleId/books/$bookId/chapters",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<ChapterListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }

            }
        }
    }

    /**
     * Gets a single Chapter object for a given audioBibleId and chapterId.
     * This AudioChapter object also includes an resourceUrl property with a HTTP URL
     * to the mp3 audio resource for the chapter. The resourceUrl is unique per request
     * and expires in XX minutes. The expiresAt property provides the Unix time value of
     * resourceUrl expiration.
     * @param bibleId Id of Bible whose Chapter to fetch
     * @param chapterId Id of the Chapter to fetch
     */
    fun getChapter(
        bibleId: String,
        chapterId: String
    ): Flow<AudioChapter?> {

        val request = super.buildRequest(
            "/$bibleId/chapters/$chapterId",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<AudioChapterResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }

            }
        }
    }
}

@Serializable
data class AudioBibleListResponse(val data: List<AudioBibleSummary>)

@Serializable
data class AudioBibleResponse(val data: AudioBible)

@Serializable
data class AudioChapterResponse(val data: AudioChapter)