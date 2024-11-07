package de.dojodev.mybiblenotes.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.dojodev.mybiblenotes.database.BibleDB
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideBibleDao(db: BibleDB): BibleDao {
        return db.bibleDao()
    }

    @Provides
    fun provideBookDao(db: BibleDB): BookDao {
        return db.bookDao()
    }

    @Provides
    fun provideChapterDao(db: BibleDB): ChapterDao {
        return db.chapterDao()
    }

    @Provides
    fun provideSectionDao(db: BibleDB): SectionDao {
        return db.sectionDao()
    }

    @Provides
    fun provideVerseDao(db: BibleDB): VerseDao {
        return db.verseDao()
    }

    @Provides
    fun providePassageDao(db: BibleDB): PassageDao {
        return db.passageDao()
    }

    @Provides
    fun provideTagDao(db: BibleDB): TagDao {
        return db.tagDao()
    }

    @Provides
    fun provideNoteDao(db: BibleDB): NoteDao {
        return db.noteDao()
    }

    @Provides
    fun provideNotePartDao(db: BibleDB): NotePartDao {
        return db.notePartDao()
    }

    @Provides
    fun provideRelationDao(db: BibleDB): RelationDao {
        return db.relationDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): BibleDB {
        return Room.databaseBuilder(
            appContext,
            BibleDB::class.java,
            "bibleDb"
        ).build()
    }
}