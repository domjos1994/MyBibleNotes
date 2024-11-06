package de.dojodev.mybiblenotes.bibleapi.model.verse

import kotlinx.serialization.Serializable

@Serializable
data class VerseSummary(
    val id: String = "",
    val orgId: String = "",
    val bibleId: String = "",
    val bookId: String = "",
    val chapterId: String = "",
    val reference: String = ""
) {
    override fun toString(): String {
        return this.id
    }
}
