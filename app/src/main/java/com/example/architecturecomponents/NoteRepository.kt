package com.example.architecturecomponents

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.architecturecomponents.dao.NoteDao
import com.example.architecturecomponents.database.NoteDatabase
import com.example.architecturecomponents.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteRepository(application: Application) {

    var noteDao: NoteDao
    var allNotes: LiveData<List<Note>>

    init {
        var database = NoteDatabase.getInstance(application)
        this.noteDao = database.noteDao()
        this.allNotes = this.noteDao.getAllNotes()
    }

    fun insert(note: Note) {

        GlobalScope.launch {
            noteDao.insert(note)
        }

    }

    fun update(note: Note) {
        GlobalScope.launch {
            noteDao.update(note)
        }
    }

    fun delete(note: Note) {
        GlobalScope.launch {
            noteDao.insert(note)
        }
    }

    fun deleteAll() {
        GlobalScope.launch {
            noteDao.deleteAllNotes()
        }
    }
}