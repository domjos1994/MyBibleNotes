package de.dojodev.mybiblenotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.dojodev.mybiblenotes.database.converter.DateConverter
import de.dojodev.mybiblenotes.database.converter.MediaTypeConverter
import de.dojodev.mybiblenotes.database.converter.NoteTypeConverter
import de.dojodev.mybiblenotes.database.converter.RelationTypeConverter
import de.dojodev.mybiblenotes.database.dao.BibleDao
import de.dojodev.mybiblenotes.database.dao.BookDao
import de.dojodev.mybiblenotes.database.dao.ChapterDao
import de.dojodev.mybiblenotes.database.dao.NoteDao
import de.dojodev.mybiblenotes.database.dao.NotePartDao
import de.dojodev.mybiblenotes.database.dao.PassageDao
import de.dojodev.mybiblenotes.database.dao.RelationDao
import de.dojodev.mybiblenotes.database.dao.SectionDao
import de.dojodev.mybiblenotes.database.dao.TagDao
import de.dojodev.mybiblenotes.database.dao.VerseDao
import de.dojodev.mybiblenotes.database.model.bibleData.Bible
import de.dojodev.mybiblenotes.database.model.bibleData.Book
import de.dojodev.mybiblenotes.database.model.bibleData.Chapter
import de.dojodev.mybiblenotes.database.model.bibleData.Passage
import de.dojodev.mybiblenotes.database.model.bibleData.Section
import de.dojodev.mybiblenotes.database.model.bibleData.Verse
import de.dojodev.mybiblenotes.database.model.noteData.Note
import de.dojodev.mybiblenotes.database.model.noteData.NotePart
import de.dojodev.mybiblenotes.database.model.noteData.Relation
import de.dojodev.mybiblenotes.database.model.noteData.Tag

@Database(entities = [
    Bible::class, Book::class, Chapter::class, Passage::class, Section::class, Verse::class,
    Relation::class, Tag::class, Note::class, NotePart::class
 ], version = 1)
@TypeConverters(MediaTypeConverter::class, DateConverter::class, RelationTypeConverter::class, NoteTypeConverter::class)
abstract class BibleDB : RoomDatabase() {

    abstract fun bibleDao(): BibleDao
    abstract fun bookDao(): BookDao
    abstract fun chapterDao(): ChapterDao
    abstract fun noteDao(): NoteDao
    abstract fun notePartDao(): NotePartDao
    abstract fun passageDao(): PassageDao
    abstract fun relationDao(): RelationDao
    abstract fun sectionDao(): SectionDao
    abstract fun tagDao(): TagDao
    abstract fun verseDao(): VerseDao
}