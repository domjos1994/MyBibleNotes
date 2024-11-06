package de.dojodev.mybiblenotes.bibleapi.model.verse

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: String = "", val bookId: String = "")
