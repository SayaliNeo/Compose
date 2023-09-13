@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.neospacecompose.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.neospacecompose.components.NoteButton
import com.example.neospacecompose.components.NoteInputText
import com.example.neospacecompose.model.Note
import com.example.neospacecompose.ui.theme.CardBackground
import com.example.neospacecompose.ui.theme.Purple71


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController
) {

    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(100) {
               /* Card(
                    backgroundColor = Purple71,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    elevation = 8.dp,
                ) {
                    Text(
                        text = "$it",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }*/
            }
        })
}


/*
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
    HomeScreen(
        navController = rememberNavController(),
        notes = emptyList(),
        onAddNote = {}
    ) {}
}*/
