package de.dojodev.mybiblenotes.database.model.bibleData

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "sections",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Section(
    @PrimaryKey
    var id: String = "",
    var bibleId: String = "",
    var bookId: String = "",
    var chapterId: String = "",
    var title: String = "",
    var content: String = "",
    var verseCount: Int = -1,
    var firstVerseId: String = "",
    var lastVerseId: String = "",
    var firstVerseOrgId: String = "",
    var lastVerseOrgId: String = "",
    var copyright: String = "",
) {
    override fun toString(): String {
        return this.title
    }
}
