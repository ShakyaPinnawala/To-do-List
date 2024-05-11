package com.example.todoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todoapp.databinding.ActivityAddnotesBinding
import com.example.todoapp.databinding.ActivityMainBinding

class Addnotes : AppCompatActivity() {

    private  lateinit var binding: ActivityAddnotesBinding
    private  lateinit var db:DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddnotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.savebutton.setOnClickListener{
            val title=binding.titeledittext.text.toString()
            val content= binding.contentedittext.text.toString()
            val note=Note(0,title,content)
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Note Saved !",Toast.LENGTH_SHORT).show()

        }

    }
}