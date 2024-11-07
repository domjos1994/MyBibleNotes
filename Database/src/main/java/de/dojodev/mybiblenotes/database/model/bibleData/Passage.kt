package de.dojodev.mybiblenotes.database.model.bibleData

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "passages",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Passage(
    @PrimaryKey
    var id: String,
    var bibleId: String,
    var chapterId: String,
    var verseId: String,
    var orgId: String = "",
    var content: String = "",
    var reference: String = "",
    var verseCount: Int = -1,
    var copyright: String = ""
) {
    override fun toString(): String {
        return this.content
    }
}
