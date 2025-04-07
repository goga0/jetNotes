package com.r4men.notes.domain

import com.r4men.notes.data.NoteDao
import com.r4men.notes.data.models.Note

interface NoteRepository {
    suspend fun createNote(noteDao: NoteDao)
    suspend fun getNote(noteDao: NoteDao): Note
}