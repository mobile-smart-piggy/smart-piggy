package com.example.mobilesmartpiggy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.ColumnInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: PigsState,
    onEvent: (PigsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = "Add Pig") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField()
            }
        }
    )
}