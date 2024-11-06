package de.dojodev.mybiblenotes.bibleapi.model.book

import de.dojodev.mybiblenotes.bibleapi.model.chapter.ChapterSummary
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String = "",
    val bibleId: String = "",
    val abbreviation: String = "",
    val name: String = "",
    val nameLong: String = "",
    val chapters: List<ChapterSummary>? = listOf()
) {

    override fun toString(): String {
        return this.name
    }

}