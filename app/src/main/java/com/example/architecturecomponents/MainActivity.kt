package com.example.architecturecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.architecturecomponents.database.NoteDatabase
import com.example.architecturecomponents.model.Note
import com.example.architecturecomponents.vm.NoteViewModel

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProviders.of(this@MainActivity).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this@MainActivity, Observer<List<Note>> {
            Toast.makeText(this@MainActivity,it[0].title,Toast.LENGTH_SHORT).show()
        })

    }
}
