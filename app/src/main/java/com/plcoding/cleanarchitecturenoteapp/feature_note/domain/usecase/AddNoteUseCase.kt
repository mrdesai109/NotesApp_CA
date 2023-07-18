package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNoteUseCase(val repository: NoteRepository) {

    @Throws(Exception::class)
    operator suspend fun invoke(note : Note){
        if(note.title.isBlank()){
            throw Exception("Title is empty")
        }
        if(note.content.isBlank()){
            throw Exception("Content is empty")
        }
        repository.insertNote(note)
    }
}