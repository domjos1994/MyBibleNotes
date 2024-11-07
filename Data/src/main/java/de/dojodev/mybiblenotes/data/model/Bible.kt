package de.dojodev.mybiblenotes.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import de.dojodev.mybiblenotes.bibleapi.model.bible.Bible as ServiceBible
import de.dojodev.mybiblenotes.bibleapi.model.bible.BibleSummary as ServiceBibleSummary
import de.dojodev.mybiblenotes.bibleapi.model.bible.AudioBibleSummary as ServiceAudioBibleSummary
import de.dojodev.mybiblenotes.bibleapi.model.bible.AudioBible as ServiceAudioBible
import de.dojodev.mybiblenotes.database.model.bibleData.Bible as DBBible

data class Bible(
    var id: String,
    var abbreviation: String = "",
    var abbreviationLocal: String = "",
    var copyright: String = "",
    var language: Locale? = null,
    var countries: List<Locale> = listOf(),
    var name: String = "",
    var nameLocal: String = "",
    var description: String = "",
    var descriptionLocal: String = "",
    var info: String = "",
    var type: String = "",
    var updateAt: Date? = Date(),
    var relatedDbl: String = "",
    var mediaType: MediaType = MediaType.Book
) {
    var books = mutableListOf<Book>()

    override fun toString(): String {
        return this.name
    }

    companion object {
        private const val FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

        fun fromServiceBibleSummary(serviceBibleSummary: ServiceBibleSummary): Bible {
            val sdf = SimpleDateFormat(FORMAT, Locale.getDefault())
            return Bible(
                serviceBibleSummary.id,
                serviceBibleSummary.abbreviation,
                serviceBibleSummary.abbreviationLocal,
                "",
                Locale(serviceBibleSummary.language?.name ?: ""),
                serviceBibleSummary.countries.map { Locale(it.name) },
                serviceBibleSummary.name,
                serviceBibleSummary.nameLocal,
                serviceBibleSummary.description,
                serviceBibleSummary.descriptionLocal,
                "",
                serviceBibleSummary.type,
                sdf.parse(serviceBibleSummary.updateAt),
                serviceBibleSummary.relatedDbl,
                MediaType.Book
            )
        }
        fun fromServiceAudioBibleSummary(serviceAudioBibleSummary: ServiceAudioBibleSummary): Bible {
            return Bible(
                serviceAudioBibleSummary.id,
                "",
                "",
                "",
                null,
                listOf(),
                serviceAudioBibleSummary.name,
                serviceAudioBibleSummary.nameLocal,
                serviceAudioBibleSummary.description,
                serviceAudioBibleSummary.descriptionLocal,
                "",
                "",
                null,
                "",
                MediaType.AudioBook
            )
        }

        fun fromServiceBible(serviceBible: ServiceBible): Bible {
            val sdf = SimpleDateFormat(FORMAT, Locale.getDefault())
            return Bible(
                serviceBible.id,
                serviceBible.abbreviation,
                serviceBible.abbreviationLocal,
                serviceBible.copyright,
                Locale(serviceBible.language?.name ?: ""),
                serviceBible.countries.map { Locale(it.name) },
                serviceBible.name,
                serviceBible.nameLocal,
                serviceBible.description,
                serviceBible.descriptionLocal,
                serviceBible.info,
                serviceBible.type,
                sdf.parse(serviceBible.updateAt),
                serviceBible.relatedDbl,
                MediaType.Book
            )
        }

        fun fromServiceBible(serviceAudioBible: ServiceAudioBible): Bible {
            val sdf = SimpleDateFormat(FORMAT, Locale.getDefault())
            return Bible(
                serviceAudioBible.id,
                serviceAudioBible.abbreviation,
                serviceAudioBible.abbreviationLocal,
                serviceAudioBible.copyright,
                Locale(serviceAudioBible.language?.name ?: ""),
                serviceAudioBible.countries.map { Locale(it.name) },
                serviceAudioBible.name,
                serviceAudioBible.nameLocal,
                serviceAudioBible.description,
                serviceAudioBible.descriptionLocal,
                serviceAudioBible.info,
                serviceAudioBible.type,
                sdf.parse(serviceAudioBible.updateAt),
                serviceAudioBible.relatedDbl,
                MediaType.Book
            )
        }

        fun fromDbBible(dbBible: DBBible): Bible {
            return Bible(
                dbBible.id,
                dbBible.abbreviation,
                dbBible.abbreviationLocal,
                dbBible.copyright,
                Locale(dbBible.language),
                dbBible.countries.split(",").map { Locale(it) },
                dbBible.name,
                dbBible.nameLocal,
                dbBible.description,
                dbBible.descriptionLocal,
                dbBible.info,
                dbBible.type,
                dbBible.updateAt,
                "",
                when(dbBible.mediaType) {
                    de.dojodev.mybiblenotes.database.model.bibleData.MediaType.Book -> MediaType.Book
                    de.dojodev.mybiblenotes.database.model.bibleData.MediaType.AudioBook -> MediaType.AudioBook
                    else -> MediaType.Book
                }
            )
        }

        fun toDbBible(bible: Bible): DBBible {
            return DBBible(
                bible.id,
                bible.abbreviation,
                bible.abbreviationLocal,
                bible.copyright,
                bible.language?.language ?: "",
                bible.countries.joinToString(",") { it.language },
                bible.name,
                bible.nameLocal,
                bible.description,
                bible.descriptionLocal,
                bible.info,
                bible.type,
                bible.updateAt ?: Date(),
                when(bible.mediaType) {
                    MediaType.Book -> de.dojodev.mybiblenotes.database.model.bibleData.MediaType.Book
                    MediaType.AudioBook -> de.dojodev.mybiblenotes.database.model.bibleData.MediaType.AudioBook
                }
            )
        }
    }
}

enum class MediaType {
    Book,
    AudioBook
}
