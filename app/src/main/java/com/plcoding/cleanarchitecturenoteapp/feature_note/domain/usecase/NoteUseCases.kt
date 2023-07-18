package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase

data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getSingleNoteUseCase: GetSingleNoteUseCase,
)
