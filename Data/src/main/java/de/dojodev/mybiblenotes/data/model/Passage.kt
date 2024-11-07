package de.dojodev.mybiblenotes.data.model

import de.dojodev.mybiblenotes.bibleapi.model.passage.Passage as ServicePassage
import de.dojodev.mybiblenotes.database.model.bibleData.Passage as DBPassage

data class Passage(
    var id: String,
    var orgId: String = "",
    var content: String = "",
    var reference: String = "",
    var verseCount: Int = -1,
    var copyright: String = ""
) {
    override fun toString(): String {
        return this.content
    }

    companion object {

        fun fromServicePassage(servicePassage: ServicePassage): Passage {
            return Passage(
                servicePassage.id,
                servicePassage.orgId,
                servicePassage.content,
                servicePassage.reference,
                servicePassage.verseCount,
                servicePassage.copyright
            )
        }

        fun fromDbPassage(dbPassage: DBPassage): Passage {
            return Passage(
                dbPassage.id,
                dbPassage.orgId,
                dbPassage.content,
                dbPassage.reference,
                dbPassage.verseCount,
                dbPassage.copyright
            )
        }

        fun toDbPassage(passage: Passage, bibleId: String, chapterId: String, verseId: String): DBPassage {
            return DBPassage(
                passage.id,
                bibleId,
                chapterId,
                verseId,
                passage.orgId,
                passage.content,
                passage.reference,
                passage.verseCount,
                passage.copyright
            )
        }
    }
}
