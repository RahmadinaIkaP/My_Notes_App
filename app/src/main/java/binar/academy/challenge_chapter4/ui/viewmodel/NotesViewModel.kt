package binar.academy.challenge_chapter4.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import binar.academy.challenge_chapter4.data.room.NotesDatabase
import binar.academy.challenge_chapter4.data.model.Notes
import binar.academy.challenge_chapter4.data.room.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var getAllData : LiveData<List<Notes>>
    private val notesRepository : NotesRepository

    init {
        val notesDao = NotesDatabase.getInstance(application)?.noteDao()
        notesRepository = NotesRepository(notesDao!!)
        getAllData = notesRepository.getAllDataNotes
    }

    fun addNote(notes: Notes){
        viewModelScope.launch {
            notesRepository.addNote(notes)
        }
    }

    fun editNotes(notes: Notes){
        viewModelScope.launch {
            notesRepository.editNote(notes)
        }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch {
            notesRepository.deleteNote(notes)
        }
    }
}