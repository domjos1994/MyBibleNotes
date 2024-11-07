package de.dojodev.mybiblenotes.data.model

import de.dojodev.mybiblenotes.bibleapi.model.book.Book as ServiceBook
import de.dojodev.mybiblenotes.database.model.bibleData.Book as DBBook

data class Book(
    var id: String,
    var abbreviation: String = "",
    var name: String = "",
    var nameLong: String = ""
) {
    var chapters = mutableListOf<Chapter>()

    override fun toString(): String {
        return this.name
    }

    companion object {

        fun fromServiceBook(serviceBook: ServiceBook): Book {
            return Book(
                serviceBook.id,
                serviceBook.abbreviation,
                serviceBook.name,
                serviceBook.nameLong
            )
        }

        fun fromDbBook(dbBook: DBBook): Book {
            return Book(
                dbBook.id,
                dbBook.abbreviation,
                dbBook.name,
                dbBook.nameLong
            )
        }

        fun toDbChapter(book: Book, bibleId: String): DBBook {
            return DBBook(
                book.id,
                bibleId,
                book.abbreviation,
                book.name,
                book.nameLong
            )
        }
    }
}