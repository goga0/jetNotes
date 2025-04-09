package com.r4men.notes.presentation.screens.noteDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.r4men.notes.data.models.Note
import com.r4men.notes.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(NoteDetailsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                getNote()
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
            is NoteDetailsAction.NoteTitleChanged -> _state.value.note?.title = action.newTitle
            is NoteDetailsAction.NoteValueChanged -> _state.value.note?.noteValue = action.newValue
            is NoteDetailsAction.SaveNote -> saveNote()
        }
    }

    fun saveNote(){
        viewModelScope.launch(Dispatchers.IO) {
            if(_state.value.note != null){
                noteRepository.createNote(_state.value.note)
                    .onSuccess { _state.value.infoMessage = "Заметка успешно сохранена" }
                    .onFailure { error ->
                        _state.value.infoMessage = "Не удалось сохранить заметку: $error"
                    }
            }
        }
    }

    fun getNote(){
        viewModelScope.launch(Dispatchers.IO){
                noteRepository.getNoteById(_state.value.note?.id!!)
                    .onSuccess{ _state.value.note = it }
                    .onFailure{
                        error -> _state.value.infoMessage = "$error"
                        delay(2000)
                        hasLoadedInitialData = false
                    }
        }
    }
}