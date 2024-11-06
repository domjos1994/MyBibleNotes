package de.dojodev.mybiblenotes.bibleapi.model.bible

import kotlinx.serialization.Serializable

@Serializable
data class AudioBibleSummary(
    val id: String = "",
    val name: String = "",
    val nameLocal: String = "",
    val description: String = "",
    val descriptionLocal: String = ""
) {
    override fun toString(): String {
        return this.nameLocal
    }
}