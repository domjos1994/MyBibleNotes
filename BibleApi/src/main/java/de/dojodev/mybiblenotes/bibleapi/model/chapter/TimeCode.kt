package de.dojodev.mybiblenotes.bibleapi.model.chapter

import kotlinx.serialization.Serializable

@Serializable
data class TimeCode(val end: String = "", val start: String = "", val verseId: String = "")
