package de.dojodev.mybiblenotes.bibleapi.model.base

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val id: String = "",
    val name: String = "",
    val nameLocal: String = "",
    val script: String = "",
    val scriptDirection: String = ""
) {
    override fun toString(): String {
        return this.nameLocal
    }
}
