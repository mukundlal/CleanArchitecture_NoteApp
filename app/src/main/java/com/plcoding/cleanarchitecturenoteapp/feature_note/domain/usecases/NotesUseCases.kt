package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases

data class NotesUseCases(
    val getNotes: GetNotes,val deleteNode: DeleteNode,val addNote: AddNote
)
