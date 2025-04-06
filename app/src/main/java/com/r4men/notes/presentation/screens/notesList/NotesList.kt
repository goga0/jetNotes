package com.r4men.notes.presentation.screens.notesList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.r4men.notes.R
import com.r4men.notes.data.models.Note
import com.r4men.notes.presentation.ui.components.MainScreenScaffold
import com.r4men.notes.presentation.ui.theme.NotesTheme

@Composable
fun NotesListRoot(
    viewModel: NotesListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NotesListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun NotesListScreen(
    state: NotesListState,
    onAction: (NotesListAction) -> Unit,
) {
    MainScreenScaffold { innerPadding ->
        val lazyGridState = rememberLazyGridState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.onSecondary)
        ) {
            if(state.notes != null){
                NotesList(notes = state.notes, state = lazyGridState)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.EmptyNotesList),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    NotesTheme {
        NotesListScreen(
            state = NotesListState(),
            onAction = {}
        )
    }
}

@Composable
fun NotesList(
    notes: List<Note>,
    state: LazyGridState
){
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Adaptive(minSize = 300.dp)
    ) {
        items(notes){ note ->
            Card{
                Text(
                    text = note.noteValue ?: "",
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}