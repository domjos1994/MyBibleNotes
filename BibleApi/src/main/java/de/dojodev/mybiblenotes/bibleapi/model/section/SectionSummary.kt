package de.dojodev.mybiblenotes.bibleapi.model.section

import kotlinx.serialization.Serializable

@Serializable
data class SectionSummary(
    val id: String = "",
    val bibleId: String = "",
    val bookId: String = "",
    val title: String = "",
    val firstVerseId: String = "",
    val lastVerseId: String = "",
    val firstVerseOrgId: String = "",
    val lastVerseOrgId: String = ""
) {
    override fun toString(): String {
        return this.title
    }
}
