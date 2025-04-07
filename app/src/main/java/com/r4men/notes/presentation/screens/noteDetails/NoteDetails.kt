package com.r4men.notes.presentation.screens.noteDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.r4men.notes.presentation.ui.components.DetailsScaffold
import com.r4men.notes.presentation.ui.theme.NotesTheme

@Composable
fun NoteDetailsRoot(
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    noteId: Int
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteDetailsScreen(
        state = state,
        onAction = viewModel::onAction,
        noteId = noteId
    )
}

@Composable
fun NoteDetailsScreen(
    state: NoteDetailsState,
    onAction: (NoteDetailsAction) -> Unit,
    noteId: Int = 0
) {

    DetailsScaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = state.noteValue ?: "",
                onValueChange = { newValue: String -> state.noteValue = newValue }
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
            onAction = {}
        )
    }
}