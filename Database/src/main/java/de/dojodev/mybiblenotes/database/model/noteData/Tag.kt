package de.dojodev.mybiblenotes.database.model.noteData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "tags",
    indices = [Index("id", orders = [Index.Order.ASC], unique = true)]
)
data class Tag(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var title: String,
    @ColumnInfo("description", defaultValue = "")
    var description: String = ""
) {
    override fun toString(): String {
        return this.title
    }
}
