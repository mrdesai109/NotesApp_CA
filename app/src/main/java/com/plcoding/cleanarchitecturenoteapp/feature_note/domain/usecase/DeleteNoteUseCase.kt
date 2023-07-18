package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class DeleteNoteUseCase(val repository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}