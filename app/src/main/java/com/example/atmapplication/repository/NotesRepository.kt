package com.example.atmapplication.repository

import com.example.atmapplication.db.NotesDataBase
import com.example.atmapplication.dto.Notes

class NotesRepository(val db: NotesDataBase) {
    suspend fun upsert(notes: Notes) = db.getNotesDao().upsert(notes)
    fun getSavedNotes() = db.getNotesDao().getAllNotes()
}