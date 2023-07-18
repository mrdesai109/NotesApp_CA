package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase.NoteUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class NotesViewModel constructor(
    val noteUseCases: NoteUseCases
) : ViewModel() {

    //state
    private val _state = mutableStateOf<NotesState>(NotesState())
    val state: State<NotesState> = _state

    var lastDeletedNote: Note? = null
    var job1: Job? = null

    init {
        getNotesBasedOnOrderType(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvents) {
        when (event) {
            is NotesEvents.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    lastDeletedNote = event.note
                }
            }

            is NotesEvents.Order -> {
                if ((state.value.noteOrder::class == event.noteOrder::class) && (state.value.noteOrder.orderType == event.noteOrder.orderType)) {
                    return
                }
                getNotesBasedOnOrderType(event.noteOrder)
            }

            is NotesEvents.RestoreNote -> {
                viewModelScope.launch {
                    lastDeletedNote?.let {
                        noteUseCases.addNoteUseCase(it)
                        lastDeletedNote = null
                    }
                }
            }

            is NotesEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotesBasedOnOrderType(noteOrder1: NoteOrder) {
        job1?.cancel()
        job1 = viewModelScope.launch {
            noteUseCases.getNotes(noteOrder1).collectLatest { notes1 ->
                _state.value = state.value.copy(
                    notes = notes1,
                    noteOrder = noteOrder1
                )
            }
        }
    }

}