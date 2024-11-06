package de.dojodev.mybiblenotes.bibleapi.model.section

import kotlinx.serialization.Serializable

@Serializable
data class Section(
    val id: String = "",
    val bibleId: String = "",
    val bookId: String = "",
    val chapterId: String = "",
    val title: String = "",
    val content: String = "",
    val verseCount: Int = -1,
    val firstVerseId: String = "",
    val lastVerseId: String = "",
    val firstVerseOrgId: String = "",
    val lastVerseOrgId: String = "",
    val copyright: String = "",
    val next: Item? = null,
    val previous: Item? = null
) {
    override fun toString(): String {
        return this.title
    }
}

