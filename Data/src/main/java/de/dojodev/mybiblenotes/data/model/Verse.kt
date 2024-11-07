package de.dojodev.mybiblenotes.data.model

import de.dojodev.mybiblenotes.bibleapi.model.verse.Verse as ServiceVerse
import de.dojodev.mybiblenotes.bibleapi.model.verse.VerseSummary as ServiceVerseSummary
import de.dojodev.mybiblenotes.database.model.bibleData.Verse as DBVerse

data class Verse(
    var id: String = "",
    var orgId: String = "",
    var content: String = "",
    var reference: String = "",
    var verseCount: Int = -1,
    var copyright: String = "",
) {
    override fun toString(): String {
        return this.content
    }

    companion object {

        fun fromServiceVerseSummary(serviceVerseSummary: ServiceVerseSummary): Verse {
            return Verse(
                serviceVerseSummary.id,
                serviceVerseSummary.orgId,
                "",
                serviceVerseSummary.reference,
                -1,
                ""
            )
        }

        fun fromServiceVerse(serviceVerse: ServiceVerse): Verse {
            return Verse(
                serviceVerse.id,
                serviceVerse.orgId,
                serviceVerse.content,
                serviceVerse.reference,
                serviceVerse.verseCount,
                serviceVerse.copyright
            )
        }

        fun fromDbVerse(dbVerse: DBVerse): Verse {
            return Verse(
                dbVerse.id,
                dbVerse.orgId,
                dbVerse.content,
                dbVerse.reference,
                dbVerse.verseCount,
                dbVerse.copyright
            )
        }

        fun toDbVerse(verse: Verse, bibleId: String, bookId: String, chapterId: String): DBVerse {
            return DBVerse(
                verse.id,
                verse.orgId,
                bibleId,
                bookId,
                chapterId,
                verse.content,
                verse.reference,
                verse.verseCount,
                verse.copyright
            )
        }
    }
}
