package de.dojodev.mybiblenotes.bibleapi.model.passage

import kotlinx.serialization.Serializable

@Serializable
data class Passage(
    val id: String = "",
    val bibleId: String = "",
    val orgId: String = "",
    val content: String = "",
    val reference: String = "",
    val verseCount: Int = -1,
    val copyright: String = ""
) {
    override fun toString(): String {
        return this.content
    }
}
