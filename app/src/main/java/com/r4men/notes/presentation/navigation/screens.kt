package com.r4men.notes.presentation.navigation

import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import com.r4men.notes.R


@Serializable
sealed class Screens() {

    @Serializable
    object NotesList: Screens()

    @Serializable
    class NoteDetails(val title: String,val noteId: Int): Screens()

    @Serializable
    class Settings(val title: String): Screens()
}