package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NotesRepository

class DeleteNode(private val repository: NotesRepository) {

    suspend  operator fun invoke(note: Note){
        repository.deleteNote(note)
    }

}