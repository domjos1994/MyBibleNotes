package de.dojodev.mybiblenotes.database.model.bibleData

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    "bibles",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Bible(
    @PrimaryKey
    var id: String,
    var abbreviation: String = "",
    var abbreviationLocal: String = "",
    var copyright: String = "",
    var language: String = "",
    var countries: String = "",
    var name: String,
    var nameLocal: String = "",
    var description: String = "",
    var descriptionLocal: String = "",
    var info: String = "",
    var type: String = "",
    var updateAt: Date = Date(),
    var mediaType: MediaType = MediaType.Book
) {
    override fun toString(): String {
        return this.name
    }
}

enum class MediaType {
    Book,
    AudioBook
}