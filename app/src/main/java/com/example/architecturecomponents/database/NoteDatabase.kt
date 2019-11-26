package com.example.architecturecomponents.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.architecturecomponents.dao.NoteDao
import com.example.architecturecomponents.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class],version = 1)
abstract class NoteDatabase : RoomDatabase(){

    companion object{
        var instance : NoteDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : NoteDatabase {
            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            GlobalScope.launch {
                                var database = instance as NoteDatabase
                                database.noteDao().insert(Note("note from callback","note desc",2))
                            }
                            super.onCreate(db)
                        }
                    })
                    .build()
            }
            return instance as NoteDatabase
        }


    }

    abstract fun noteDao():NoteDao

}