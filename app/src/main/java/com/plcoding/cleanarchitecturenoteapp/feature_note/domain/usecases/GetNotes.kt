package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NotesRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NotesOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NotesRepository
) {

    operator fun invoke(
        notesOrder: NotesOrder=NotesOrder.Date(OrderType.Ascending)
    ):Flow<List<Note>>{

        return repository.getNotes().map { list->

            when(notesOrder.orderType)
            {
                is OrderType.Ascending->{

                    when(notesOrder)
                    {
                       is  NotesOrder.Title->
                            list.sortedBy { note -> note.title.lowercase() }
                       is  NotesOrder.Date->
                            list.sortedBy { note -> note.timestamp }
                       is  NotesOrder.Color->
                            list.sortedBy { note -> note.color }
                    }

                }
               is OrderType.Descending->{
                    when(notesOrder)
                    {
                       is NotesOrder.Title->
                            list.sortedByDescending { note -> note.title.lowercase() }
                       is  NotesOrder.Date->
                            list.sortedByDescending { note -> note.timestamp }
                       is  NotesOrder.Color->
                            list.sortedByDescending { note -> note.color }
                    }
                }
            }

        }

    }
}