package com.example.architecturecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturecomponents.model.Note
import com.example.architecturecomponents.vm.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var noteAdapter = NoteAdapter()
        recycler_view.adapter = noteAdapter
        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.setHasFixedSize(true)
        noteViewModel = ViewModelProviders.of(this@MainActivity).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this@MainActivity, Observer<List<Note>> {
            noteAdapter.setNotes(it)
        })


    }
}

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var items: List<Note>? = null

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.text_view_title)
        var description = view.findViewById<TextView>(R.id.text_view_description)
        var priority = view.findViewById<TextView>(R.id.text_view_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    fun setNotes(notes: List<Note>) {
        items = notes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note = items!![position]
        holder.title.text = note.title
        holder.description.text = note.description
        holder.priority.text = note.priority.toString()
    }
}