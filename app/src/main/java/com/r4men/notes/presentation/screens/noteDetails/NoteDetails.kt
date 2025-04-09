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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.r4men.notes.data.models.Note
import com.r4men.notes.presentation.ui.components.DetailsScaffold
import com.r4men.notes.presentation.ui.theme.NotesTheme

@Composable
fun NoteDetailsRoot(
    viewModel: NoteDetailsViewModel = hiltViewModel(),
    noteId: Int,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteDetailsScreen(
        state = state,
        onAction = viewModel::onAction,
        noteId = noteId,
        navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(
    state: NoteDetailsState,
    onAction: (NoteDetailsAction) -> Unit,
    noteId: Int = 0,
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        state.noteId = noteId
    }

    DetailsScaffold(
        title = state.note?.title ?: "Заметки",
        onBackPressed = {
            onAction(NoteDetailsAction.SaveNote)
            navController.popBackStack()
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = state.note?.noteValue ?: "",
                onValueChange = { onAction(NoteDetailsAction.NoteValueChanged(it)) },
                colors = textFieldColors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
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
            noteId = 0,
            navController = rememberNavController()
        )
    }
}