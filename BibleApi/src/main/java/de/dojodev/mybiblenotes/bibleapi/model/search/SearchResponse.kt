package de.dojodev.mybiblenotes.bibleapi.model.search

import de.dojodev.mybiblenotes.bibleapi.model.passage.Passage
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val query: String? = "",
    val limit: Int = -1,
    val offset: Int = -1,
    val total: Int = -1,
    val verseCount: Int = -1,
    val verses: List<SearchVerse>? = listOf(),
    val passages: List<Passage>? = listOf()
) {
    override fun toString(): String {
        return "$query"
    }
}