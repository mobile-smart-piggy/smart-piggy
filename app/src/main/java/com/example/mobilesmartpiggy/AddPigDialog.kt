package com.example.mobilesmartpiggy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.ColumnInfo
import androidx.room.util.TableInfo

@Composable
fun AddPigDialog(
    state: PigsState,
    onEvent: (PigsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(PigsEvent.HideDialog)
        },
        title = { Text(text = "Add Pig") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.parentFemale,
                    onValueChange = {
                        onEvent(PigsEvent.SetParentFemale(it))
                    },
                    placeholder = {
                        Text(text = "Parent Female")
                    }
                )
                TextField(
                    value = state.parentMale,
                    onValueChange = {
                        onEvent(PigsEvent.SetParentMale(it))
                    },
                    placeholder = {
                        Text(text = "Parent Male")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(PigsEvent.SavePig)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}