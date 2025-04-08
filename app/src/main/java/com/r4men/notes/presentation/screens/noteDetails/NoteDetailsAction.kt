package com.r4men.notes.presentation.screens.noteDetails

sealed interface NoteDetailsAction {
    class NoteTitleChanged(val newTitle: String): NoteDetailsAction
    class NoteValueChanged(val newValue: String): NoteDetailsAction
}