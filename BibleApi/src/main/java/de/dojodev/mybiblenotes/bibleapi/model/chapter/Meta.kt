package de.dojodev.mybiblenotes.bibleapi.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val fums: String = "",
    val fumsId: String = "",
    val fumsJsInclude: String = "",
    val fumsJs: String = "",
    val fumsNoScript: String = ""
)
