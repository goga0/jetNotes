package com.r4men.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.r4men.notes.data.NoteDao
import com.r4men.notes.data.models.Note


@Database(
    entities = [(Note::class)],
    version = 1
    )
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}