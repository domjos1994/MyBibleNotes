package de.dojodev.mybiblenotes.bibleapi.model.section

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: String = "", val title: String = "") {
    override fun toString(): String {
        return this.title
    }
}
