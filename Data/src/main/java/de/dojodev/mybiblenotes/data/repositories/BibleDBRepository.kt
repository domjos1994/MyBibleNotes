package de.dojodev.mybiblenotes.data.repositories

import de.dojodev.mybiblenotes.data.model.Bible
import de.dojodev.mybiblenotes.data.model.Book
import de.dojodev.mybiblenotes.data.model.Chapter
import de.dojodev.mybiblenotes.data.model.Passage
import de.dojodev.mybiblenotes.data.model.Section
import de.dojodev.mybiblenotes.data.model.Verse
import de.dojodev.mybiblenotes.database.dao.BibleDao
import de.dojodev.mybiblenotes.database.dao.BookDao
import de.dojodev.mybiblenotes.database.dao.ChapterDao
import de.dojodev.mybiblenotes.database.dao.PassageDao
import de.dojodev.mybiblenotes.database.dao.SectionDao
import de.dojodev.mybiblenotes.database.dao.VerseDao
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface BibleDBRepository {

    suspend fun getBibles(): List<Bible>
    suspend fun updateBible(bible: Bible)
    suspend fun deleteBible(bible: Bible)
}

class DefaultBibleDBRepository @Inject constructor(
    private val bibleDao: BibleDao,
    private val bookDao: BookDao,
    private val chapterDao: ChapterDao,
    private val sectionDao: SectionDao,
    private val passageDao: PassageDao,
    private val verseDao: VerseDao
) : BibleDBRepository {

    override suspend fun getBibles(): List<Bible> {
        val bibles = bibleDao.getAll().first().map { Bible.fromDbBible(it) }
        bibles.map { bible ->
            bible.books.addAll(bookDao.getAll(bible.id).first().map { Book.fromDbBook(it) })

            bible.books.map { book ->
                book.chapters.addAll(chapterDao.getAll(bible.id, book.id).first().map { Chapter.fromDbChapter(it) })

                book.chapters.map { chapter ->

                    chapter.sections.addAll(sectionDao.getAll(bible.id, book.id, chapter.id).first().map { Section.fromDbSection(it) })
                    chapter.passages.addAll(passageDao.getAll(bible.id, chapter.id).first().map { Passage.fromDbPassage(it) })
                    chapter.verses.addAll(verseDao.getAll(bible.id, book.id, chapter.id).first().map { Verse.fromDbVerse(it) })
                }
            }
        }
        return bibles
    }

    override suspend fun updateBible(bible: Bible) {
        val dbBible = Bible.toDbBible(bible)
        val hasBible = this.bibleDao.count(dbBible.id) != 0L
        if(hasBible) {
            this.bibleDao.updateBible(dbBible)
        } else {
            this.bibleDao.insertBible(dbBible)
        }

        bible.books.forEach { book ->
            val dbBook = Book.toDbChapter(book, dbBible.id)
            val hasBook = this.bookDao.count(dbBook.id) != 0L
            if(hasBook) {
                this.bookDao.updateBook(dbBook)
            } else {
                this.bookDao.insertBook(dbBook)
            }

            book.chapters.forEach { chapter ->
                val dbChapter = Chapter.toDbChapter(chapter, dbBible.id, dbBook.id)
                val hasChapter = this.chapterDao.count(dbChapter.id) != 0L
                if(hasChapter) {
                    this.chapterDao.updateChapter(dbChapter)
                } else {
                    this.chapterDao.insertChapter(dbChapter)
                }

                chapter.sections.forEach { section ->
                    val dbSection = Section.toDbSection(section, dbBible.id, dbBook.id, dbChapter.id)
                    val hasSection = this.sectionDao.count(dbSection.id) != 0L
                    if(hasSection) {
                        this.sectionDao.updateSection(dbSection)
                    } else {
                        this.sectionDao.insertSection(dbSection)
                    }
                }
                chapter.passages.forEach { passage ->
                    val dbPassage = Passage.toDbPassage(passage, dbBible.id, dbBook.id, dbChapter.id)
                    val hasPassage = this.passageDao.count(dbPassage.id) != 0L
                    if(hasPassage) {
                        this.passageDao.updatePassage(dbPassage)
                    } else {
                        this.passageDao.insertPassage(dbPassage)
                    }
                }
                chapter.verses.forEach { verse ->
                    val dbVerse = Verse.toDbVerse(verse, dbBible.id, dbBook.id, dbChapter.id)
                    val hasVerse = this.verseDao.count(dbVerse.id) != 0L
                    if(hasVerse) {
                        this.verseDao.updateVerse(dbVerse)
                    } else {
                        this.verseDao.insertVerse(dbVerse)
                    }
                }
            }
        }
    }

    override suspend fun deleteBible(bible: Bible) {
        val dbBible = Bible.toDbBible(bible)
        this.bibleDao.deleteBible(dbBible)

        bible.books.forEach { book ->
            val dbBook = Book.toDbChapter(book, dbBible.id)
            this.bookDao.deleteBook(dbBook)

            book.chapters.forEach { chapter ->
                val dbChapter = Chapter.toDbChapter(chapter, dbBible.id, dbBook.id)
                this.chapterDao.deleteChapter(dbChapter)

                chapter.sections.forEach { section ->
                    val dbSection = Section.toDbSection(section, dbBible.id, dbBook.id, dbChapter.id)
                    this.sectionDao.deleteSection(dbSection)
                }
                chapter.passages.forEach { passage ->
                    val dbPassage = Passage.toDbPassage(passage, dbBible.id, dbBook.id, dbChapter.id)
                    this.passageDao.deletePassage(dbPassage)
                }
                chapter.verses.forEach { verse ->
                    val dbVerse = Verse.toDbVerse(verse, dbBible.id, dbBook.id, dbChapter.id)
                    this.verseDao.deleteVerse(dbVerse)
                }
            }
        }
    }

}