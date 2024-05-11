package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper
    private  lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=DatabaseHelper(this)
        noteAdapter= NoteAdapter(db.getAllNotes(),this)

        binding.noterecycleview.layoutManager= LinearLayoutManager(this)
        binding.noterecycleview.adapter= noteAdapter

        binding.addbutton.setOnClickListener{
            val intent= Intent(this,Addnotes::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        noteAdapter.refreshData(db.getAllNotes())

    }
}
