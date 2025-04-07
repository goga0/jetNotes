package com.r4men.notes.presentation.screens.noteDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(): ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NoteDetailsState())
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
            initialValue = NoteDetailsState()
        )

    fun onAction(action: NoteDetailsAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

}