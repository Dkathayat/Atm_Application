package com.example.atmapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atmapplication.dto.Notes
import com.example.atmapplication.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository): ViewModel() {

    private val allNotes = notesRepository.getSavedNotes()
    val notesData: MutableLiveData<List<Notes>> = MutableLiveData()

    fun saveNotesToDb(notes: Notes) = viewModelScope.launch {
        notesRepository.upsert(notes)
    }
    fun getSavedNotes(): LiveData<List<Notes>>{
        return allNotes
    }

}