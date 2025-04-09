package com.r4men.notes.presentation.screens.notesList

sealed interface NotesListAction {
    object RefreshNotes: NotesListAction
}