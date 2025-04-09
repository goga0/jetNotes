package com.r4men.notes.presentation.screens.notesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4men.notes.data.models.Note
import com.r4men.notes.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("REDUNDANT_ELSE_IN_WHEN")
@HiltViewModel
class NotesListViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NotesListState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadNotes()
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
            is NotesListAction.RefreshNotes -> loadNotes()
        }
    }

    fun loadNotes(){
        viewModelScope.launch(Dispatchers.IO){
            noteRepository.getAllNotes()
                .onSuccess { _state.value.notes = it }
                .onFailure { error -> _state.value.errorMessage = "$error" }
        }
    }
}