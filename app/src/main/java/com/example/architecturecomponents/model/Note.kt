package com.example.architecturecomponents.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// the default is the class name
@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var description: String,
    var priority: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)