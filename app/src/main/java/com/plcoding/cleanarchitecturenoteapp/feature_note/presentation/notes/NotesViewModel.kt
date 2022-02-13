package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.usecases.NotesUseCases
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NotesOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) :ViewModel(){

    private val _state= mutableStateOf(NotesStates())
    val states:State<NotesStates> = _state
    private var  recentlyDeleteNote:Note?=null

    init {

        getNotes(NotesOrder.Date(OrderType.Descending))
    }

    private var recentJob:Job?=null
    fun onEvent(notesEvents: NotesEvents)
    {
        when(notesEvents)
        {
            is NotesEvents.OrderEvent->
            {

                if (states.value.notesOrder::orderType==notesEvents.notesOrder::class&&
                        states.value.notesOrder.orderType==notesEvents.notesOrder.orderType)
                {
                    return
                }
                getNotes(notesEvents.notesOrder)



            }
            is NotesEvents.DeleteNode->
            {
                viewModelScope.launch {
                    notesUseCases.deleteNode(notesEvents.note)
                    recentlyDeleteNote=notesEvents.note
                }

            }
            is NotesEvents.ToggleOrderSection->
            {
                _state.value=states.value.copy(
                    isOrderingVisible = !states.value.isOrderingVisible
                )
            }
            is NotesEvents.RestoreNode->
            {
                    viewModelScope.launch {
                        notesUseCases.addNote(recentlyDeleteNote?:return@launch)
                        recentlyDeleteNote=null
                    }
            }
        }

    }

    private fun getNotes(notesOrder: NotesOrder) {
        recentJob?.cancel()
        recentJob= notesUseCases.getNotes(notesOrder)
            .onEach { list ->

                _state.value=states.value.copy(
                    notes = list,
                    notesOrder = notesOrder
                )
            }.launchIn(viewModelScope)

    }

}