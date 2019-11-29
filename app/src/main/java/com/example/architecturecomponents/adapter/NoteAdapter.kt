package com.example.architecturecomponents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturecomponents.R
import com.example.architecturecomponents.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var items: List<Note>? = null

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.text_view_title)
        var description = view.findViewById<TextView>(
            R.id.text_view_description
        )
        var priority = view.findViewById<TextView>(
            R.id.text_view_priority
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item, parent, false)
        return NoteViewHolder(
            view
        )
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