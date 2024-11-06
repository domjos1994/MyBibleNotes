package de.dojodev.mybiblenotes.bibleapi.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class Item(val id: String = "", val bookId: String = "", val name: String = "")