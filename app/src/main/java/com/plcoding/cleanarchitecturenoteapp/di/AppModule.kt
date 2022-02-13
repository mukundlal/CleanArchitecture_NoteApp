package com.plcoding.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.data_source.NotesDao
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NotesRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases.AddNote
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases.DeleteNode
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases.GetNotes
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases.NotesUseCases
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
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return  Room.databaseBuilder(app
        ,NoteDatabase::class.java
        ,"note_db")
            .build()
    }


    @Provides
    @Singleton
    fun provideNotesRepository(db: NoteDatabase):NotesRepository{
        return NoteRepositoryImpl(db.notesDao)

    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NotesRepository):NotesUseCases
    {
        return NotesUseCases(
            getNotes = GetNotes(repository),
            deleteNode = DeleteNode(repository),
            addNote = AddNote(repository)
        )
    }
}