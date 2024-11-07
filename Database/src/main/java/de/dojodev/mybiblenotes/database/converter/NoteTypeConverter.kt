package de.dojodev.mybiblenotes.database.converter

import androidx.room.TypeConverter
import de.dojodev.mybiblenotes.database.model.noteData.NoteType

class NoteTypeConverter {
    private val note = "note"
    private val notePart = "note-part"

    @TypeConverter
    fun fromEnum(value: NoteType): String {
        return when(value) {
            NoteType.Note -> this.note
            NoteType.NotePart -> this.notePart
        }
    }

    @TypeConverter
    fun fromString(value: String): NoteType {
        return when(value) {
            this.note -> NoteType.Note
            this.notePart -> NoteType.NotePart
            else -> NoteType.Note
        }
    }
}