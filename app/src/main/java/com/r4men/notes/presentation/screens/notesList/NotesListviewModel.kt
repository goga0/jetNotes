package com.r4men.notes.presentation.screens.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4men.notes.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NotesListState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = NotesListState()
        )

    fun onAction(action: NotesListAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}