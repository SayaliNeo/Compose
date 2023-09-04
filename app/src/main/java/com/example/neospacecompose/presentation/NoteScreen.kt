package com.example.neospacecompose.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.neospacecompose.components.NoteButton
import com.example.neospacecompose.components.NoteInputText
import com.example.neospacecompose.model.Note
import com.example.neospacecompose.ui.theme.CardBackground
import com.example.neospacecompose.ui.theme.Purple71
import com.example.neospacecompose.viewmodel.model.NoteViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NotesApp(navController: NavHostController, noteViewModel : NoteViewModel = hiltViewModel()) {
    val notesList = noteViewModel.notelist.collectAsState().value
    NoteScreen(
        navController,
        notes = notesList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNotes(it) }
    )
}

@Composable
fun NoteScreen(
    navController: NavHostController, notes: List<Note>,
    onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(3.dp)
    ) {
        Card(
            backgroundColor = CardBackground,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                NoteInputText(
                    modifier = Modifier.padding(top = 9.dp, bottom = 10.dp),
                    text = title,
                    label = "Title",
                    onTextChange = {
                        if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                    })
                NoteInputText(
                    modifier = Modifier.padding(top = 9.dp, bottom = 10.dp),
                    text = description,
                    label = "Add a Note",
                    onTextChange = {
                        description = it
                    })
                NoteButton(
                    modifier = Modifier.padding(top = 9.dp, bottom = 10.dp),
                    text = "Save",
                    onClick = {
                        if (title.isNotEmpty() && description.isNotEmpty()) {
                            onAddNote(
                                Note(
                                    title = title,
                                    description = description
                                )
                            )
                            title = ""
                            description = ""
                        }
                    },
                    color = Purple71
                )
            }
        }
        Divider(modifier = Modifier.padding(10.dp))

        /*   LazyColumn {
               items(notes) { noteList ->
                   NoteColumn(note = noteList, onNoteClick = {
                       onRemoveNote(it)
                   })

               }
           }*/

        LazyColumn {
            items(items = notes) { list ->
                NoteColumn(
                    note = list,
                    onNoteClick = { onRemoveNote(it) })
            }
        }

    }


}


@Composable
fun NoteColumn(modifier: Modifier = Modifier, note: Note, onNoteClick: (Note) -> Unit) {
    Column {
        Surface(
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(bottomEnd = 10.dp, topStart = 10.dp))
                .fillMaxWidth(),
            color = CardBackground,
            elevation = 6.dp
        ) {
            Column(modifier = modifier
                .clickable { onNoteClick(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.Start) {
                Text(text = note.title, style = MaterialTheme.typography.subtitle2)
                Text(text = note.description, style = MaterialTheme.typography.subtitle1)
                //Text(text = note., style = MaterialTheme.typography.subtitle1)

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewMainContent() {
    NotesApp(
        navController = rememberNavController()
    )
}
