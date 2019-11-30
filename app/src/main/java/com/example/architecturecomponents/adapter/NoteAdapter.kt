package com.example.architecturecomponents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturecomponents.R
import com.example.architecturecomponents.model.Note

class NoteAdapter :
    ListAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem.title == newItem.title && oldItem.description == newItem.description
                        && oldItem.priority == newItem.priority

        }
    }

    //    var items: List<Note>? = null
    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item, parent, false
        )
        return NoteViewHolder(
            view
        )
    }

    fun getNoteAt(position: Int): Note = getItem(position)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var note = getItem(position)
        holder.title.text = note.title
        holder.description.text = note.description
        holder.priority.text = note.priority.toString()
    }

    inner class NoteViewHolder : RecyclerView.ViewHolder {
        var title: TextView
        var description: TextView
        var priority: TextView

        constructor(view: View) : super(view) {
            title = view.findViewById<TextView>(R.id.text_view_title)
            description = view.findViewById<TextView>(
                R.id.text_view_description
            )
            priority = view.findViewById<TextView>(
                R.id.text_view_priority
            )

            view.setOnClickListener {
                if (listener != null && adapterPosition != RecyclerView.NO_POSITION)
                    listener.onItemClick(getItem(adapterPosition))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}