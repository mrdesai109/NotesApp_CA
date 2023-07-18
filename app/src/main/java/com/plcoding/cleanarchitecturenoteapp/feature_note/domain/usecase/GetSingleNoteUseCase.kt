package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class GetSingleNoteUseCase(
    val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteByID(id)
    }
}