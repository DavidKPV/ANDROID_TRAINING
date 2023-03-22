package com.davidkpv.mynotes.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.davidkpv.mynotes.Note
import com.davidkpv.mynotes.NotesDatabase
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel (private val db: NotesDatabase) : ViewModel() {
    // Not access from outside
    private val _state = MutableStateFlow(<List<Note>>(emptyList<>()))
    // Access from outside
    val state : LiveData<List<Note>> = _state

    fun onResume(){
        viewModelScope.launch{
            _state.value = db.notesDao().getAll()
        }
    }

    fun onNoteDelete(note : Note){
        viewModelScope.launch{
            db.notesDao().delete(note)
            _state.value = db.notesDao().getAll()
        }
    }
}

class MainViewModelFactory(private val db: NotesDatabase) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
}