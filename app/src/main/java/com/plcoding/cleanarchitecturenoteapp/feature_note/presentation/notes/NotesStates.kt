package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NotesOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

data class NotesStates(
    val notes:List<Note> = emptyList(),
    val notesOrder: NotesOrder= NotesOrder.Date(OrderType.Descending),
    val isOrderingVisible:Boolean=false
)