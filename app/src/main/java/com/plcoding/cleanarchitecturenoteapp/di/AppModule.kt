package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase.AddNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase.DeleteNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase.GetNotesUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase.GetSingleNoteUseCase
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDB(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, "note_db").build()
    }

    @Provides
    @Singleton
    fun provideNotePrepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.getNoteDAO())
    }

    @Provides
    @Singleton
    fun provideUseCaseMasterClass(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository = repository),
            deleteNote = DeleteNoteUseCase(repository = repository),
            addNoteUseCase = AddNoteUseCase(repository = repository),
            getSingleNoteUseCase = GetSingleNoteUseCase(repository = repository)
        )
    }
}