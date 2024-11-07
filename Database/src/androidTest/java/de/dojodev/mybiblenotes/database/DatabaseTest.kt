package de.dojodev.mybiblenotes.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

abstract class DatabaseTest {
    protected lateinit var db: BibleDB

    protected fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        this.db = Room.inMemoryDatabaseBuilder(
            context, BibleDB::class.java).build()
    }

    abstract fun init()

    abstract fun close()

    fun closeDB() {
        this.db.close()
    }
}