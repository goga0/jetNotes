package com.r4men.notes.domain

import com.r4men.notes.data.dao.NoteDao
import com.r4men.notes.data.models.Note

interface NoteRepository {
    suspend fun createNote(note: Note)
    suspend fun getAllNotes(): List<Note>?
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: Int)
}