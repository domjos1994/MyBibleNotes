package de.dojodev.mybiblenotes.database.model.bibleData

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "chapters",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Chapter(
    @PrimaryKey
    var id: String,
    var bibleId: String,
    var bookId: String,
    var number: String = "",
    var content: String = "",
    var reference: String = "",
    var verseCount: Int = -1,
    var copyright: String = ""
) {
    override fun toString(): String {
        return this.number
    }
}
