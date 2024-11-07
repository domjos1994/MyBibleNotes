package de.dojodev.mybiblenotes.database.converter

import androidx.room.TypeConverter
import de.dojodev.mybiblenotes.database.model.bibleData.MediaType

class MediaTypeConverter {

    companion object {
        const val BOOK = "book"
        const val AUDIO_BOOK = "audio-book"
    }

    @TypeConverter
    fun fromEnum(value: MediaType): String {
        return when(value) {
            MediaType.Book -> BOOK
            MediaType.AudioBook -> AUDIO_BOOK
        }
    }

    @TypeConverter
    fun fromString(value: String): MediaType {
        return when(value) {
            BOOK -> MediaType.Book
            AUDIO_BOOK -> MediaType.AudioBook
            else -> MediaType.Book
        }
    }
}