package de.dojodev.mybiblenotes.bibleapi.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val id: String = "",
    val bibleId: String = "",
    val number: String = "",
    val bookId: String = "",
    val content: String = "",
    val reference: String = "",
    val verseCount: Int = -1,
    val next: Item? = null,
    val previous: Item? = null,
    val copyright: String = ""
) {
    override fun toString(): String {
        return this.number
    }
}