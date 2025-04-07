package com.r4men.notes.data.repository

import com.r4men.notes.data.dao.NoteDao
import com.r4men.notes.data.models.Note
import com.r4men.notes.domain.NoteRepository
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository {

    override suspend fun createNote(note: Note) {
        noteDao.createNote(note)
    }

    override suspend fun getAllNotes(): List<Note>? {
        return noteDao.getAllNotes()
    }

    override suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(id: Int){
        return noteDao.deleteNoteById(id)
    }
}