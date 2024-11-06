package de.dojodev.mybiblenotes.bibleapi.model.bible

import de.dojodev.mybiblenotes.bibleapi.model.base.Country
import de.dojodev.mybiblenotes.bibleapi.model.base.Language
import kotlinx.serialization.Serializable

@Serializable
data class Bible(
    val id: String = "",
    val dblId: String = "",
    val abbreviation: String = "",
    val abbreviationLocal: String = "",
    val copyright: String = "",
    val language: Language? = null,
    val countries: List<Country> = listOf(),
    val name: String = "",
    val nameLocal: String = "",
    val description: String = "",
    val descriptionLocal: String = "",
    val info: String = "",
    val type: String = "",
    val updateAt: String = "",
    val relatedDbl: String = "",
    val audioBibles: List<AudioBibleSummary> = listOf()
) {
    override fun toString(): String {
        return this.nameLocal
    }
}
