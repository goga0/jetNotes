package com.r4men.notes.presentation.screens.noteDetails

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
class NoteDetailsViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NoteDetailsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                noteRepository.getNoteById(_state.value.note!!.id)
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = NoteDetailsState()
        )

    fun onAction(action: NoteDetailsAction) {
        when (action) {
            is NoteDetailsAction.NoteTitleChanged -> _state.value.note!!.title = action.newTitle
            is NoteDetailsAction.NoteValueChanged -> _state.value.note!!.noteValue = action.newValue
        }
    }

}