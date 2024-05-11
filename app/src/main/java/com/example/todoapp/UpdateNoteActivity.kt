package com.example.todoapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var  db: DatabaseHelper

    private var noteId: Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= DatabaseHelper(this)
        noteId= intent.getIntExtra("note_id",-1)
        if(noteId==-1){
            finish()
            return
        }

        val note= db.getNoteByID(noteId)
        binding.updatetiteledittext.setText(note.title)
        binding.updatecontentedittext.setText(note.content)

        binding.updatesavebutton.setOnClickListener{
            val newTitle = binding.updatetiteledittext.text.toString()
            val newContent = binding.updatecontentedittext.text.toString()
            val updatedNote = Note(noteId,newTitle,newContent)

            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Update saved...",Toast.LENGTH_SHORT).show()
        }

    }
}