package de.dojodev.mybiblenotes.bibleapi.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class ChapterSummary(
    val id: String = "",
    val bibleId: String = "",
    val number: String = "",
    val bookId: String = "",
    val reference: String = ""
) {
    override fun toString(): String {
        return this.number
    }
}
