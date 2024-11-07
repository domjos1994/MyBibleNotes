package de.dojodev.mybiblenotes.database.model.bibleData

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "books",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Book(
    @PrimaryKey
    var id: String,
    var bibleId: String,
    var abbreviation: String = "",
    var name: String = "",
    var nameLong: String = "",
) {
    override fun toString(): String {
        return this.name
    }
}