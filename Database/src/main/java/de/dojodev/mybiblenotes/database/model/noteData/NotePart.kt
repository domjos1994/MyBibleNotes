package de.dojodev.mybiblenotes.database.model.noteData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    "noteParts",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class NotePart(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var noteId: Long,
    var title: String,
    @ColumnInfo("description", defaultValue = "")
    var description: String = "",
    @ColumnInfo("content", defaultValue = "")
    var content: String = "",
    var insertDate: Date,
    var updateDate: Date = Date()
) {
    override fun toString(): String {
        return this.title
    }
}
