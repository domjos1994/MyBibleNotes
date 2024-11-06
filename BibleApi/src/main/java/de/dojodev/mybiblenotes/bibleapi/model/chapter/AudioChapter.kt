package de.dojodev.mybiblenotes.bibleapi.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class AudioChapter(
    val id: String = "",
    val bibleId: String = "",
    val number: String = "",
    val bookId: String = "",
    val resourceUrl: String = "",
    val timecodes: List<TimeCode>? = listOf(),
    val expiresId: Int = -1,
    val reference: String = "",
    val next: Item? = null,
    val previous: Item? = null,
    val copyright: String = ""
) {

    override fun toString(): String {
        return this.number
    }
}