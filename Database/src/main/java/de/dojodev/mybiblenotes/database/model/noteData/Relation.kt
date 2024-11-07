package de.dojodev.mybiblenotes.database.model.noteData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "relations",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Relation(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var foreignId: String,
    var foreignType: RelationType,
    var noteId: String,
    var noteType: NoteType,
    @ColumnInfo("title", defaultValue = "")
    var title: String,
    @ColumnInfo("note", defaultValue = "")
    var note: String = "",
    @ColumnInfo("description", defaultValue = "")
    var description: String = "",
    @ColumnInfo("content", defaultValue = "")
    var content: String = "",
) {
    override fun toString(): String {
        return this.title
    }
}

enum class RelationType {
    Bible,
    Book,
    Chapter,
    Passage,
    Section,
    Verse,
    Note,
    NotePart,
    Tag
}

enum class NoteType {
    Note,
    NotePart
}
