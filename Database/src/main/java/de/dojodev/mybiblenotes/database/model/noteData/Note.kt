package de.dojodev.mybiblenotes.database.model.noteData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    "notes",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
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
