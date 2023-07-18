package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder

sealed class NotesEvents{
    class Order(val noteOrder: NoteOrder) : NotesEvents()

    class DeleteNote(val note: Note) : NotesEvents()

    object RestoreNote : NotesEvents()

    object ToggleOrderSection : NotesEvents()
}
