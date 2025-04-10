package com.r4men.notes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.r4men.notes.data.models.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun createNote(note: Note)

    @Query("SELECT * FROM note WHERE id =:id")
    suspend fun getNoteById(id: Int): Note

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>?

    @Query("DELETE FROM note WHERE id=:id")
    suspend fun deleteNoteById(id: Int)
}