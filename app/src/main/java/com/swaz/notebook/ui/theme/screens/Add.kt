package com.swaz.notebook.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swaz.notebook.data.NoteView

@Composable
fun Add(
    noteView: NoteView,
    userId: String,
    noteId: String?,
    onSave: () -> Unit,

    )
{
    val ext = noteId?.let{
        noteView.getNoteById(it)
    }
    var title by remember { mutableStateOf(ext?.title ?: "") }
    var content by remember { mutableStateOf(ext?.content ?:"") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    )
    {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Enter your notes") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            maxLines = Int.MAX_VALUE
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick =
                {
                    if (noteId != null)
                        {
                            noteView.updateNote(
                                userId,
                                noteId,
                                title,
                                content
                            )
                            { onSave() }
                        }
                    else
                    {
                        noteView.addNote(
                            userId,
                            title,
                            content
                        )
                        { onSave() }

                    }
                },
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(
                "Save Note"
            )
        }

    }


}