package de.dojodev.mybiblenotes.data.repositories

import de.dojodev.mybiblenotes.bibleapi.requests.AudioBibleRequest
import de.dojodev.mybiblenotes.bibleapi.requests.BibleRequest
import de.dojodev.mybiblenotes.data.model.Book
import de.dojodev.mybiblenotes.data.model.Bible
import de.dojodev.mybiblenotes.data.model.Chapter
import de.dojodev.mybiblenotes.data.model.Passage
import de.dojodev.mybiblenotes.data.model.Section
import de.dojodev.mybiblenotes.data.model.Verse
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface BibleServiceRepository {
    var bible: Bible
    var book: Book
    var chapter: Chapter

    suspend fun getBibles(): List<Bible>
    suspend fun getBooks(bible: Bible): List<Book>
    suspend fun getChapters(book: Book): List<Chapter>
    suspend fun getPassage(chapter: Chapter): Passage?
    suspend fun getSections(chapter: Chapter): List<Section>
    suspend fun getSection(section: Section): Section?
    suspend fun getVerses(chapter: Chapter): List<Verse>
    suspend fun getVerse(verse: Verse): Verse?
}

class DefaultBibleServiceRepository @Inject constructor(
    private val service: BibleRequest,
    override var bible: Bible,
    override var book: Book,
    override var chapter: Chapter
) : BibleServiceRepository {

    override suspend fun getBibles(): List<Bible> {
        return service.getBibles(full = true).first().map { Bible.fromServiceBibleSummary(it) }
    }

    override suspend fun getBooks(bible: Bible): List<Book> {
        val detail = service.getBible(bible.id).first()
        if(detail != null) {
            this.bible = Bible.fromServiceBible(detail)
        } else {
            this.bible = bible
        }
        return service.getBooks(this.book.id).first().map { Book.fromServiceBook(it) }
    }

    override suspend fun getChapters(book: Book): List<Chapter> {
        val detail = service.getBook(bible.id, book.id).first()
        if(detail != null) {
            this.book = Book.fromServiceBook(detail)
        } else {
            this.book = book
        }
        val found = this.bible.books.find { it.id == this.book.id }
        if(found == null) {
            this.bible.books.add(this.book)
        }

        return service.getChapters(this.bible.id, this.book.id).first().map { Chapter.fromServiceChapterSummary(it) }
    }

    override suspend fun getPassage(chapter: Chapter): Passage? {
        val detail = service.getChapter(this.bible.id, chapter.id).first()
        if(detail != null) {
            this.chapter = Chapter.fromServiceChapter(detail)
        } else {
            this.chapter = chapter
        }
        val found = this.book.chapters.find { it.id == this.chapter.id }
        if(found == null) {
            this.book.chapters.add(this.chapter)
        }

        val passage = service.getPassage(this.bible.id, this.chapter.id).first()
        if(passage != null) {
            val v = Passage.fromServicePassage(passage)

            val p = this.chapter.passages.find { it.id == passage.id }
            if(p == null) {
                this.chapter.passages.add(v)
            }
            return v
        }

        return null
    }

    override suspend fun getSections(chapter: Chapter): List<Section> {
        val detail = service.getChapter(this.bible.id, chapter.id).first()
        if(detail != null) {
            this.chapter = Chapter.fromServiceChapter(detail)
        } else {
            this.chapter = chapter
        }
        val found = this.book.chapters.find { it.id == this.chapter.id }
        if(found == null) {
            this.book.chapters.add(this.chapter)
        }

        return service.getSectionsInChapter(this.bible.id, this.chapter.id).first().map { Section.fromServiceSectionSummary(it) }
    }

    override suspend fun getSection(section: Section): Section? {
        val tmp = service.getSection(this.bible.id, section.id).first()
        if(tmp != null) {
            val v = Section.fromServiceSection(tmp)

            val found = this.chapter.verses.find { it.id == tmp.id }
            if(found == null) {
                this.chapter.sections.add(v)
            }
            return v
        }
        return null
    }

    override suspend fun getVerses(chapter: Chapter): List<Verse> {
        val detail = service.getChapter(this.bible.id, chapter.id).first()
        if(detail != null) {
            this.chapter = Chapter.fromServiceChapter(detail)
        } else {
            this.chapter = chapter
        }
        val found = this.book.chapters.find { it.id == this.chapter.id }
        if(found == null) {
            this.book.chapters.add(this.chapter)
        }

        return service.getVerses(this.bible.id, this.chapter.id).first().map { Verse.fromServiceVerseSummary(it) }
    }

    override suspend fun getVerse(verse: Verse): Verse? {
        val tmp = service.getVerse(this.bible.id, verse.id).first()
        if(tmp != null) {
            val v = Verse.fromServiceVerse(tmp)

            val found = this.chapter.verses.find { it.id == tmp.id }
            if(found == null) {
                this.chapter.verses.add(v)
            }

            return v
        }
        return null
    }
}

class DefaultAudioBibleServiceRepository @Inject constructor(
    private val service: AudioBibleRequest,
    override var bible: Bible,
    override var book: Book,
    override var chapter: Chapter
) : BibleServiceRepository {

    override suspend fun getBibles(): List<Bible> {
        return service.getBibles(full = true).first().map { Bible.fromServiceAudioBibleSummary(it) }
    }

    override suspend fun getBooks(bible: Bible): List<Book> {
        val detail = service.getBible(bible.id).first()
        if(detail != null) {
            this.bible = Bible.fromServiceBible(detail)
        } else {
            this.bible = bible
        }
        return service.getBooks(this.book.id).first().map { Book.fromServiceBook(it) }
    }

    override suspend fun getChapters(book: Book): List<Chapter> {
        val detail = service.getBook(bible.id, book.id).first()
        if(detail != null) {
            this.book = Book.fromServiceBook(detail)
        } else {
            this.book = book
        }
        return service.getChapters(this.bible.id, this.book.id).first().map { Chapter.fromServiceChapterSummary(it) }
    }

    override suspend fun getPassage(chapter: Chapter): Passage? {
        val detail = service.getChapter(this.bible.id, chapter.id).first()
        if(detail != null) {
            this.chapter = Chapter.fromServiceAudioChapter(detail)
        } else {
            this.chapter = chapter
        }
        return null
    }

    override suspend fun getSections(chapter: Chapter): List<Section> {
        val detail = service.getChapter(this.bible.id, chapter.id).first()
        if(detail != null) {
            this.chapter = Chapter.fromServiceAudioChapter(detail)
        } else {
            this.chapter = chapter
        }
        return listOf()
    }

    override suspend fun getSection(section: Section): Section? {
        return null
    }

    override suspend fun getVerses(chapter: Chapter): List<Verse> {
        val detail = service.getChapter(this.bible.id, chapter.id).first()
        if(detail != null) {
            this.chapter = Chapter.fromServiceAudioChapter(detail)
        } else {
            this.chapter = chapter
        }
        return listOf()
    }

    override suspend fun getVerse(verse: Verse): Verse? {
        return null
    }
}