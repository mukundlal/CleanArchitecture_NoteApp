package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NotesOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType


@Composable
fun OrderSection(
    modifier: Modifier= Modifier,
    notesOrder: NotesOrder=NotesOrder.Date(OrderType.Descending),
    onOrderChange:(noteOrder:NotesOrder)->Unit
) {

    Column (
        modifier=modifier
    )
    {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Title", selected = notesOrder is NotesOrder.Title, onSelect = {
                onOrderChange(NotesOrder.Title(notesOrder.orderType))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Date", selected = notesOrder is NotesOrder.Title, onSelect = {
                onOrderChange(NotesOrder.Date(notesOrder.orderType))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Color", selected = notesOrder is NotesOrder.Title, onSelect = {
                onOrderChange(NotesOrder.Color(notesOrder.orderType))
            })

        }
        Spacer(modifier = Modifier.heightIn(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Ascending", selected =notesOrder.orderType is OrderType.Ascending , onSelect = {
                onOrderChange(notesOrder.changeOrderType(OrderType.Ascending))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = "Descending", selected = notesOrder.orderType is OrderType.Descending, onSelect = {
                onOrderChange(notesOrder.changeOrderType(OrderType.Descending))
            })

        }

    }
}