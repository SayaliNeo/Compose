package com.example.neospacecompose.viewmodel.model

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neospacecompose.model.Note
import com.example.neospacecompose.repository.NoteRepository
import com.example.neospacecompose.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val repository: NoteRepository) : ViewModel() {

    //  private val notelist = mutableStateListOf<Note>()
    private val _notelist = MutableStateFlow<List<Note>>(emptyList())
    val notelist = _notelist.asStateFlow()

    init {
        // getAllNotes()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect { listOfNotes ->
                if (listOfNotes.isNullOrEmpty()) {
                    Log.d("Empty", ": Empty List")
                } else {
                    _notelist.value = listOfNotes
                }
            }
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note = note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note = note) }

    fun removeNotes(note: Note) = viewModelScope.launch { repository.deleteNote(note = note) }

    /*fun addNote(note: Note) {
       notelist.add(note)
   }*/

    /*   fun removeNote(note: Note) {
           notelist.remove(note)
       }

       fun getAllNotes(): List<Note> {
           return notelist
       }*/

}