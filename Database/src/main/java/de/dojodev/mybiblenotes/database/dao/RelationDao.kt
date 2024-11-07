package de.dojodev.mybiblenotes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.dojodev.mybiblenotes.database.model.noteData.Relation
import de.dojodev.mybiblenotes.database.model.noteData.RelationType
import kotlinx.coroutines.flow.Flow

@Dao
interface RelationDao {

    @Query("SELECT * FROM relations")
    fun getAll(): Flow<List<Relation>>

    @Query("SELECT * FROM relations WHERE foreignId=:id AND foreignType=:type")
    fun getRelationByType(id: String, type: RelationType): Flow<List<Relation>>

    @Insert
    fun insertRelation(relation: Relation): Long

    @Update
    fun updateRelation(relation: Relation)

    @Delete
    fun deleteRelation(relation: Relation)
}