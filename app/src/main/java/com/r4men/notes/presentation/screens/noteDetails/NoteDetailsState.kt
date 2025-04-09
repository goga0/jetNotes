package com.r4men.notes.presentation.screens.noteDetails

import com.r4men.notes.data.models.Note

data class NoteDetailsState(
    var noteId: Int? = null,
    var note: Note? = null,
    var infoMessage: String? = null
)