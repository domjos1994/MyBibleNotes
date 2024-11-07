package de.dojodev.mybiblenotes.database.converter

import androidx.room.TypeConverter
import de.dojodev.mybiblenotes.database.model.noteData.RelationType

class RelationTypeConverter {
    private val bible = "bible"
    private val book = "book"
    private val chapter = "chapter"
    private val passage = "passage"
    private val section = "section"
    private val verse = "verse"
    private val note = "note"
    private val notePart = "note-part"
    private val tag = "tag"

    @TypeConverter
    fun fromEnum(value: RelationType): String {
        return when(value) {
            RelationType.Bible -> this.bible
            RelationType.Book -> this.book
            RelationType.Chapter -> this.chapter
            RelationType.Passage -> this.passage
            RelationType.Section -> this.section
            RelationType.Verse -> this.verse
            RelationType.Note -> this.note
            RelationType.NotePart -> this.notePart
            RelationType.Tag -> this.tag
        }
    }

    @TypeConverter
    fun fromString(value: String): RelationType {
        return when(value) {
            this.bible -> RelationType.Bible
            this.book -> RelationType.Bible
            this.chapter -> RelationType.Chapter
            this.passage -> RelationType.Passage
            this.section -> RelationType.Section
            this.verse -> RelationType.Verse
            this.note -> RelationType.Note
            this.notePart -> RelationType.NotePart
            this.tag -> RelationType.Tag
            else -> RelationType.Bible
        }
    }
}