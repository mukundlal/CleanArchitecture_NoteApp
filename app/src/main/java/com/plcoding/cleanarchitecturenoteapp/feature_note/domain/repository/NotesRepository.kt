package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getNotes():Flow<List<Note>>

     suspend fun getNote(id:String):Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}