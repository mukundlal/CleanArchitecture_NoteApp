package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NotesOrder

sealed class NotesEvents{

    data class OrderEvent(val notesOrder: NotesOrder):NotesEvents()
    data class DeleteNode(val note: Note):NotesEvents()
    object RestoreNode:NotesEvents()
    object ToggleOrderSection:NotesEvents()

}
