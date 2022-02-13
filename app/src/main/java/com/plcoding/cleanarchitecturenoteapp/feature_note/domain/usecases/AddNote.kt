package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NotesRepository
import kotlin.jvm.Throws

class AddNote(
    val repository: NotesRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note:Note){
        if (note.title.isBlank())
        {
            throw InvalidNoteException("Title can't be empty")
        }
        if (note.content.isBlank())
        {
            throw InvalidNoteException("Content can't be empty")
        }
        repository.insertNote(note)

    }

}