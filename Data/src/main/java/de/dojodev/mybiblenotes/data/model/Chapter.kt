package de.dojodev.mybiblenotes.data.model

import de.dojodev.mybiblenotes.bibleapi.model.chapter.Chapter as ServiceChapter
import de.dojodev.mybiblenotes.bibleapi.model.chapter.AudioChapter as ServiceAudioChapter
import de.dojodev.mybiblenotes.bibleapi.model.chapter.ChapterSummary as ServiceChapterSummary
import de.dojodev.mybiblenotes.database.model.bibleData.Chapter as DBChapter

data class Chapter(
    var id: String,
    var number: String = "",
    var content: String = "",
    var reference: String = "",
    var verseCount: Int = -1,
    var copyright: String = ""
) {
    var passages = mutableListOf<Passage>()
    var verses = mutableListOf<Verse>()
    var sections = mutableListOf<Section>()

    override fun toString(): String {
        return this.content
    }

    companion object {

        fun fromServiceChapterSummary(serviceChapterSummary: ServiceChapterSummary): Chapter {
            return Chapter(
                serviceChapterSummary.id,
                serviceChapterSummary.number,
                "",
                serviceChapterSummary.reference,
                -1,
                ""
            )
        }

        fun fromServiceChapter(serviceChapter: ServiceChapter): Chapter {
            return Chapter(
                serviceChapter.id,
                serviceChapter.number,
                serviceChapter.content,
                serviceChapter.reference,
                serviceChapter.verseCount,
                serviceChapter.copyright
            )
        }

        fun fromServiceAudioChapter(serviceAudioChapter: ServiceAudioChapter): Chapter {
            return Chapter(
                serviceAudioChapter.id,
                serviceAudioChapter.number,
                "",
                serviceAudioChapter.reference,
                -1,
                serviceAudioChapter.copyright
            )
        }

        fun fromDbChapter(dbChapter: DBChapter): Chapter {
            return Chapter(
                dbChapter.id,
                dbChapter.number,
                dbChapter.content,
                dbChapter.reference,
                dbChapter.verseCount,
                dbChapter.copyright
            )
        }

        fun toDbChapter(chapter: Chapter, bibleId: String, bookId: String): DBChapter {
            return DBChapter(
                chapter.id,
                bibleId,
                bookId,
                chapter.number,
                chapter.content,
                chapter.reference,
                chapter.verseCount,
                chapter.copyright
            )
        }
    }
}
