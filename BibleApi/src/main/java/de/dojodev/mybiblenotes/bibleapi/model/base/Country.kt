package de.dojodev.mybiblenotes.bibleapi.model.base

import kotlinx.serialization.Serializable

@Serializable
data class Country(val id: String = "", val name: String = "", val nameLocal: String = "") {
    override fun toString(): String {
        return this.nameLocal
    }
}
