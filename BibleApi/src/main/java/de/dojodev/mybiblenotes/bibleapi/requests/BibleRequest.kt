package de.dojodev.mybiblenotes.bibleapi.requests

import de.dojodev.mybiblenotes.bibleapi.model.bible.Bible
import de.dojodev.mybiblenotes.bibleapi.model.bible.BibleSummary
import de.dojodev.mybiblenotes.bibleapi.model.book.Book
import de.dojodev.mybiblenotes.bibleapi.model.chapter.Chapter
import de.dojodev.mybiblenotes.bibleapi.model.chapter.ChapterSummary
import de.dojodev.mybiblenotes.bibleapi.model.chapter.Meta
import de.dojodev.mybiblenotes.bibleapi.model.passage.Passage
import de.dojodev.mybiblenotes.bibleapi.model.search.SearchResponse
import de.dojodev.mybiblenotes.bibleapi.model.section.Section
import de.dojodev.mybiblenotes.bibleapi.model.section.SectionSummary
import de.dojodev.mybiblenotes.bibleapi.model.verse.Verse
import de.dojodev.mybiblenotes.bibleapi.model.verse.VerseSummary
import de.dojodev.mybiblenotes.bibleapi.requests.base.BasicRequest
import de.dojodev.mybiblenotes.bibleapi.requests.base.ResponseCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import java.util.Locale

/**
 * Call to endpoint bibles
 * @author Dominic Joas
 * @since 1.0
 */
class BibleRequest : BasicRequest("bibles") {

    /**
     * Gets an array of Bible objects authorized for current API Key
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
        full: Boolean = false): Flow<List<BibleSummary>> {

        val paramAbbreviation = if(abbreviation.isEmpty()) "" else "&abbreviation=$abbreviation"
        val paramName = if(name.isEmpty()) "" else "&name=$name"
        val paramIds = if(ids.isEmpty()) "" else "&ids=$ids"

        val request = super.buildRequest(
            "?language=${language.isO3Language}$paramAbbreviation" +
                    "$paramName$paramIds&include-full-details=$full",
            "GET",
            null
        )

        return flow {
            if(request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if(super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<BibleListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }
            }
        }
    }

    /**
     * Gets a single Bible for a given bibleId
     * @param bibleId Id of Bible to be fetched
     */
    fun getBible(
        bibleId: String
    ): Flow<Bible?> {

        val request = super.buildRequest(
            "/$bibleId",
            "GET",
            null
        )

        return flow {
            if(request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if(super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<BibleResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }
            }
        }
    }

    /**
     * Gets an array of Book objects for a given bibleId
     * @param bibleId Id of Bible whose Book to fetch
     * @param chapters Boolean indicating if an array of chapter summaries should be
     * included in the results. Defaults to false.
     * @param sections Boolean indicating if an array of chapter summaries and an array
     * of sections should be included in the results. Defaults to false.
     */
    fun getBooks(
        bibleId: String,
        chapters: Boolean = false,
        sections: Boolean = false
    ): Flow<List<Book>> {

        val request = super.buildRequest(
            "/$bibleId/books?include-chapters=$chapters&" +
                    "include-chapters-and-sections=$sections",
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
     * Gets a single Book object for a given bibleId and bookId
     * @param bibleId Id of Bible whose Book to fetch
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
     * Gets an array of Chapter objects for a given bibleId and bookId
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
     * Gets a single Chapter object for a given bibleId and chapterId.
     * This Chapter object also includes an content property with all verses for the Chapter.
     * @param bibleId Id of Bible whose Chapter to fetch
     * @param chapterId Id of the Chapter to fetch
     * @param contentType Content type to be returned in the content property. Supported values
     * are html (default), json (beta), and text (beta)
     * @param notes Include footnotes in content
     * @param titles Include section titles in content
     * @param chapterNumbers Include chapter numbers in content
     * @param verseNumbers Include verse numbers in content.
     * @param verseSpans Include spans that wrap verse numbers and verse text for bible content.
     * @param parallels Comma delimited list of bibleIds to include
     */
    fun getChapter(
        bibleId: String,
        chapterId: String,
        contentType: String = "html",
        notes: Boolean = false,
        titles: Boolean = true,
        chapterNumbers: Boolean = false,
        verseNumbers: Boolean = true,
        verseSpans: Boolean = false,
        parallels: String = ""
    ): Flow<Chapter?> {

        val paramParallels = if(parallels.isEmpty()) "" else "&parallels=$parallels"

        val request = super.buildRequest(
            "/$bibleId/chapters/$chapterId?content-type=$contentType&include-notes=$notes&" +
                    "include-titles=$titles&include-chapter-numbers=$chapterNumbers&" +
                    "include-verse-numbers=$verseNumbers&include-verse-spans=$verseSpans$paramParallels",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<ChapterResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }

            }
        }
    }

    /**
     * Gets an array of Section objects for a given bibleId and bookId
     * @param bibleId Id of Bible whose Sections to fetch
     * @param bookId Id of the Book whose Sections to fetch
     */
    fun getSectionsInBook(
        bibleId: String,
        bookId: String
    ): Flow<List<SectionSummary>> {

        val request = super.buildRequest(
            "/$bibleId/books/$bookId/sections",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<SectionListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }

            }
        }
    }

    /**
     * Gets an array of Section objects for a given bibleId and chapterId
     * @param bibleId Id of Bible whose Sections to fetch
     * @param chapterId Id of the Chapter whose Sections to fetch
     */
    fun getSectionsInChapter(
        bibleId: String,
        chapterId: String
    ): Flow<List<SectionSummary>> {

        val request = super.buildRequest(
            "/$bibleId/chapters/$chapterId/sections",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<SectionListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }

            }
        }
    }

    /**
     * Gets a single Section object for a given bibleId and sectionId. This Section
     * object also includes an content property with all verses for the Section.
     * @param bibleId Id of Bible whose Chapter to fetch
     * @param sectionId Id of the Section to fetch
     * @param contentType Content type to be returned in the content property. Supported values
     * are html (default), json (beta), and text (beta)
     * @param notes Include footnotes in content
     * @param titles Include section titles in content
     * @param chapterNumbers Include chapter numbers in content
     * @param verseNumbers Include verse numbers in content.
     * @param verseSpans Include spans that wrap verse numbers and verse text for bible content.
     * @param parallels Comma delimited list of bibleIds to include
     */
    fun getSection(
        bibleId: String,
        sectionId: String,
        contentType: String = "html",
        notes: Boolean = false,
        titles: Boolean = true,
        chapterNumbers: Boolean = false,
        verseNumbers: Boolean = true,
        verseSpans: Boolean = false,
        parallels: String = ""
    ): Flow<Section?> {

        val paramParallels = if(parallels.isEmpty()) "" else "&parallels=$parallels"

        val request = super.buildRequest(
            "/$bibleId/sections/$sectionId?content-type=$contentType&" +
                    "include-notes=$notes&include-titles=$titles&" +
                    "include-chapter-numbers=$chapterNumbers&include-verse-numbers=$verseNumbers&" +
                    "include-verse-spans=$verseSpans$paramParallels",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<SectionResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }

            }
        }
    }

    /**
     * Gets a Passage object for a given bibleId and passageId. This
     * Passage object also includes an content property with all verses
     * corresponding to the passageId.
     * The passageId parameter can represent a chapter, verse, or range of verses.
     * @param bibleId Id of Bible whose Chapter to fetch
     * @param passageId String reference id for the requested passage.
     * @param contentType Content type to be returned in the content property. Supported values
     * are html (default), json (beta), and text (beta)
     * @param notes Include footnotes in content
     * @param titles Include section titles in content
     * @param chapterNumbers Include chapter numbers in content
     * @param verseNumbers Include verse numbers in content.
     * @param verseSpans Include spans that wrap verse numbers and verse text for bible content.
     * @param parallels Comma delimited list of bibleIds to include
     * @param orgId Use the supplied id(s) to match the verseOrgId instead of the verseId
     */
    fun getPassage(
        bibleId: String,
        passageId: String,
        contentType: String = "html",
        notes: Boolean = false,
        titles: Boolean = true,
        chapterNumbers: Boolean = false,
        verseNumbers: Boolean = true,
        verseSpans: Boolean = false,
        parallels: String = "",
        orgId: Boolean = false
    ): Flow<Passage?> {

        val paramParallels = if(parallels.isEmpty()) "" else "&parallels=$parallels"

        val request = super.buildRequest(
            "/$bibleId/passages/$passageId?content-type=$contentType&include-notes=$notes&" +
                    "include-titles=$titles&include-chapter-numbers=$chapterNumbers&" +
                    "include-verse-numbers=$verseNumbers&include-verse-spans=$verseSpans" +
                    "$paramParallels&use-org-id=$orgId",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<PassageResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }

            }
        }
    }

    /**
     * Gets an array of Verse objects for a given bibleId and chapterId
     * @param bibleId Id of Bible whose Verses to fetch
     * @param chapterId Id of the Chapter whose Verses to fetch
     */
    fun getVerses(
        bibleId: String,
        chapterId: String
    ): Flow<List<VerseSummary>> {

        val request = super.buildRequest(
            "/$bibleId/chapters/$chapterId/verses",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<VerseListResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(listOf())
                    }
                }

            }
        }
    }

    /**
     * Gets a Verse object for a given bibleId and verseId. This
     * Verse object also includes an content property with the verse
     * corresponding to the verseId.
     * @param bibleId Id of Bible whose Chapter to fetch
     * @param verseId String reference id for the requested verse.
     * @param contentType Content type to be returned in the content property. Supported values
     * are html (default), json (beta), and text (beta)
     * @param notes Include footnotes in content
     * @param titles Include section titles in content
     * @param chapterNumbers Include chapter numbers in content
     * @param verseNumbers Include verse numbers in content.
     * @param verseSpans Include spans that wrap verse numbers and verse text for bible content.
     * @param parallels Comma delimited list of bibleIds to include
     * @param orgId Use the supplied id(s) to match the verseOrgId instead of the verseId
     */
    fun getVerse(
        bibleId: String,
        verseId: String,
        contentType: String = "html",
        notes: Boolean = false,
        titles: Boolean = true,
        chapterNumbers: Boolean = false,
        verseNumbers: Boolean = true,
        verseSpans: Boolean = false,
        parallels: String = "",
        orgId: Boolean = false
    ): Flow<Verse?> {

        val paramParallels = if(parallels.isEmpty()) "" else "&parallels=$parallels"

        val request = super.buildRequest(
            "/$bibleId/verses/$verseId?content-type=$contentType&include-notes=$notes&" +
                    "include-titles=$titles&include-chapter-numbers=$chapterNumbers&" +
                    "include-verse-numbers=$verseNumbers&include-verse-spans=$verseSpans" +
                    "$paramParallels&use-org-id=$orgId",
            "GET",
            null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<VerseResponse>(body)
                        emit(bibleListResponse.data)
                    } else {
                        emit(null)
                    }
                }

            }
        }
    }

    /**
     * Gets search results for a given bibleId and query string. Searches
     * will match all verses with the list of keywords provided in the query string.
     * Order of the keywords does not matter. However all keywords must be
     * present in a verse for it to be considered a match.
     * The total number of results returned from a search can be limited by populating the limit
     * attribute in the query string with a non-negative integer value. If no
     * limit value is provide a default of 10 is used.
     * offset can be used to traverse paginated results. So for example if you are using
     * the default limit of 10, using an offset of 10 will return the second page of results,
     * namely results 11-20.
     * The text property of each verse object contains only the verse text. It does not
     * contain footnote references. However, those can be queried directly using the
     * /bibles/{bibleId}/verses/{verseId} endpoint.
     * @param bibleId Id of Bible to search
     * @param query Search keywords or passage reference. Supported wildcards are * and ?.
     * The * wildcard matches any character sequence (e.g. searching for "wo*d" finds text such
     * as "word", "world", and "worshipped").
     * The ? wildcard matches any matches any single character (e.g. searching for "l?ve" finds
     * text such as "live" and "love").
     * @param limit Integer limit for how many matching results to return. Default is 10.
     * @param offset Offset for search results. Used to paginate results
     * @param sort Sort order of results. Supported values are relevance (default),
     * canonical and reverse-canonical
     * Available values : relevance, canonical, reverse-canonical
     * @param range One or more, comma separated, passage ids (book, chapter, verse) which the search
     * will be limited to. (i.e. gen.1,gen.5 or gen-num or gen.1.1-gen.3.5)
     * @param fuzziness Sets the fuzziness of a search to account for misspellings.
     * Values can be 0, 1, 2, or AUTO. Defaults to AUTO which varies depending on the
     */
    fun search(
        bibleId: String,
        query: String,
        limit: Int = 10,
        offset: Int = 0,
        sort: String = "relevance",
        range: String = "",
        fuzziness: String = "AUTO"
    ): Flow<SearchResponse?> {

        val paramRange = if(range.isEmpty()) "" else "&range=$range"

        val request = super.buildRequest(
            "/$bibleId/search?query=$query&limit=$limit&offset=$offset&sort=$sort" +
                    "$paramRange&fuzziness=$fuzziness",
            "GET", null
        )

        return flow {
            if (request != null) {
                super.client.newCall(request).execute().use { response ->
                    super.setState(response)

                    if (super.getState() == ResponseCode.Successful) {
                        val body = response.body?.string() ?: ""
                        val bibleListResponse = super.json.decodeFromString<Search>(body)
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
data class BibleListResponse(val data: List<BibleSummary>)

@Serializable
data class BibleResponse(val data: Bible)

@Serializable
data class BookListResponse(val data: List<Book>)

@Serializable
data class BookResponse(val data: Book)

@Serializable
data class ChapterListResponse(val data: List<ChapterSummary>)

@Serializable
data class ChapterResponse(val data: Chapter)

@Serializable
data class SectionListResponse(val data: List<SectionSummary>)

@Serializable
data class SectionResponse(val data: Section)

@Serializable
data class PassageResponse(val data: Passage)

@Serializable
data class VerseListResponse(val data: List<VerseSummary>)

@Serializable
data class VerseResponse(val data: Verse)

@Serializable
data class Search(val query: String? = "", val data: SearchResponse, val meta: Meta)