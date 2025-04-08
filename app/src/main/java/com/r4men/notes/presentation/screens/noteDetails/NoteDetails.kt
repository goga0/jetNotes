package com.r4men.notes.presentation.screens.noteDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.r4men.notes.data.models.Note
import com.r4men.notes.presentation.ui.components.DetailsScaffold
import com.r4men.notes.presentation.ui.theme.NotesTheme

@Composable
fun NoteDetailsRoot(
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    note: Note
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteDetailsScreen(
        state = state,
        onAction = viewModel::onAction,
        note = note
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    state: NoteDetailsState,
    onAction: (NoteDetailsAction) -> Unit,
    note: Note = Note(id = 0, lastSaveDate = "1970-01-01", null, null)
) {

    LaunchedEffect(Unit) {
        state.note = note
    }

    DetailsScaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = state.note?.title ?: "",
                onValueChange = { onAction(NoteDetailsAction.NoteValueChanged(it)) },
                colors = textFieldColors(

                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        NoteDetailsScreen(
            state = NoteDetailsState(),
            onAction = {},

        )
    }
}