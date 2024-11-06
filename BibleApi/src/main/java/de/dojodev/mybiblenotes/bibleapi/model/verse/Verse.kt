package de.dojodev.mybiblenotes.bibleapi.model.verse

import kotlinx.serialization.Serializable

@Serializable
data class Verse(
    val id: String = "",
    val orgId: String = "",
    val bibleId: String = "",
    val bookId: String = "",
    val chapterId: String = "",
    val content: String = "",
    val reference: String = "",
    val verseCount: Int = -1,
    val copyright: String = "",
    val next: Item? = null,
    val previous: Item? = null
) {
    override fun toString(): String {
        return this.content
    }
}
