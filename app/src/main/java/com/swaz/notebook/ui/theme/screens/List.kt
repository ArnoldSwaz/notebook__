package com.swaz.notebook.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swaz.notebook.data.AuthView
import com.swaz.notebook.data.NoteView
import com.swaz.notebook.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lst(
    navController: NavController,
    onAddNote : () -> Unit,
    onEditNote : (String) -> Unit,
    onLogout : () -> Unit


    )
{
    val authView : AuthView = viewModel()
    val noteView : NoteView = viewModel()
    val notes by noteView.notes.collectAsState()
    val noteId : String
    val userId : String
    Scaffold(
        topBar =
            {
                TopAppBar(
                    title = { Text(" Your Notes") },
                    actions = {
                        IconButton(
                            onClick = onLogout
                        ) {
                            Icon(
                                Icons.Default.ExitToApp,
                                contentDescription = "Logout"
                            )
                        }
                    }
                )

        },
        floatingActionButton =
            {
                FloatingActionButton(onClick = onAddNote)
                {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add"
                    )

                }
            }

    )
    { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
        )
        {
            items(notes){ note ->
                Itm(
                    note = note,
                    onClick = { onEditNote(note.id)}
                )

            }
        }

    }

}

@Composable
fun Itm(
    note: Note,
    onClick : () -> Unit
)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    )
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
        )
        {
            Text(
                note.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                note.content.take(100),
                style = MaterialTheme.typography.bodyMedium

            )
        }
    }

}

