package com.example.architecturecomponents

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturecomponents.adapter.NoteAdapter
import com.example.architecturecomponents.model.Note
import com.example.architecturecomponents.vm.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel
    private val ADD_NOTE_CODE = 1
    private val EDIT_NOTE_CODE = 2

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



        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var note = noteAdapter.getNoteAt(viewHolder.adapterPosition)
                noteViewModel.delete(note)
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(recycler_view)

        noteAdapter.setOnItemClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                var intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.id)
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.title)
                intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.description)
                intent.putExtra(AddEditNoteActivity.EXTRA_PR, note.priority)
                startActivityForResult(intent, EDIT_NOTE_CODE)
            }

        })

        button_add_note.setOnClickListener {
            startActivityForResult(
                Intent(this@MainActivity, AddEditNoteActivity::class.java),
                ADD_NOTE_CODE
            )
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && ADD_NOTE_CODE == requestCode) {
            var title = data?.getStringExtra(AddEditNoteActivity.EXTRA_TITLE)!!
            var description = data?.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION)!!
            var priority = data?.getIntExtra(AddEditNoteActivity.EXTRA_PR, 0)

            noteViewModel.insert(Note(title, description, priority))
            Toast.makeText(this@MainActivity, "Note Saved", Toast.LENGTH_SHORT).show()
        } else if (resultCode == Activity.RESULT_OK && EDIT_NOTE_CODE == requestCode) {
            val id: Int = data!!.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1)

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show()
                return
            }

            val title: String = data!!.getStringExtra(AddEditNoteActivity.EXTRA_TITLE)
            val description: String =
                data!!.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION)
            val priority: Int = data!!.getIntExtra(AddEditNoteActivity.EXTRA_PR, 1)

            val note = Note(title, description, priority)
            note.id = id
            noteViewModel.update(note)

            Toast.makeText(this@MainActivity, "Note updated", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Note Not Saved", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_notes -> noteViewModel.deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }
}

