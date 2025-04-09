package com.r4men.notes.domain

import com.r4men.notes.data.dao.NoteDao
import com.r4men.notes.data.models.Note

interface NoteRepository {
    suspend fun createNote(note: Note): Result<Unit>
    suspend fun getNoteById(id: Int): Result<Note?>
    suspend fun getAllNotes(): Result<List<Note>?>
    suspend fun deleteNote(id: Int): Result<Unit>
}