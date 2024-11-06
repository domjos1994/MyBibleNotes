package de.dojodev.mybiblenotes.bibleapi.model.search

import kotlinx.serialization.Serializable

@Serializable
data class SearchVerse(
    val id: String = "",
    val orgId: String = "",
    val bibleId: String = "",
    val bookId: String = "",
    val chapterId: String = "",
    val text: String = "",
    val reference: String? = ""
) {
    override fun toString(): String {
        return this.text
    }
}
