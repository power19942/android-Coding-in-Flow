package com.example.architecturecomponents.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.architecturecomponents.NoteRepository
import com.example.architecturecomponents.model.Note

class NoteViewModel(application: Application) : AndroidViewModel(application){

    var repository:NoteRepository = NoteRepository(application)
    var allNotes:LiveData<List<Note>> = repository.allNotes



    fun insert(note:Note){
        repository.insert(note)
    }

    fun update(note:Note){
        repository.update(note)
    }

    fun delete(note:Note){
        repository.delete(note)
    }

    fun deleteAll(){
        repository.deleteAll()
    }

}