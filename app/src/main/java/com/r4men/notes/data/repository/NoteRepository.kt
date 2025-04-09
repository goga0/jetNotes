package com.r4men.notes.data.repository

import com.r4men.notes.data.dao.NoteDao
import com.r4men.notes.data.models.Note
import com.r4men.notes.domain.NoteRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun createNote(note: Note): Result<Unit> {
        return runCatching{
            val formatter = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
            val currentTime = formatter.format(Date())
            val dataNote = Note(
                id = null,
                title = note.title,
                noteValue = note.noteValue,
                lastSaveDate = currentTime
            )
            noteDao.createNote(dataNote)
        }
    }

    override suspend fun getNoteById(id: Int): Result<Note>{
        return runCatching{ noteDao.getNoteById(id) }
    }

    override suspend fun getAllNotes(): Result<List<Note>?> {
        return runCatching{ noteDao.getAllNotes() }
    }

    override suspend fun deleteNote(id: Int): Result<Unit>{
        return runCatching{ noteDao.deleteNoteById(id) }
    }
}