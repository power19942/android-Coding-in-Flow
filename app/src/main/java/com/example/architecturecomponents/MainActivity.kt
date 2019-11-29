package com.example.architecturecomponents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturecomponents.adapter.NoteAdapter
import com.example.architecturecomponents.model.Note
import com.example.architecturecomponents.vm.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    val reqCode = 11010

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var noteAdapter =
            NoteAdapter()
        recycler_view.adapter = noteAdapter
        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.setHasFixedSize(true)
        noteViewModel = ViewModelProviders.of(this@MainActivity).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this@MainActivity, Observer<List<Note>> {
            noteAdapter.setNotes(it)
        })


        button_add_note.setOnClickListener {
            startActivityForResult(Intent(this@MainActivity, AddNoteActivity::class.java), reqCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && reqCode == requestCode) {
            var title = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE)!!
            var description = data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)!!
            var priority = data?.getIntExtra(AddNoteActivity.EXTRA_PR, 0)

            noteViewModel.insert(Note(title, description, priority))
            Toast.makeText(this@MainActivity,"Note Saved",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this@MainActivity,"Note Not Saved",Toast.LENGTH_SHORT).show()
        }

    }
}

