package com.example.todoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var note:List<Note>,context: Context):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    private val db: DatabaseHelper = DatabaseHelper(context)

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView=itemView.findViewById(R.id.titletextview)
        val contentTextView:TextView=itemView.findViewById(R.id.contenttextview)
        val updateButton:ImageView=itemView.findViewById(R.id.updatebutton)
        val deletButton:ImageView=itemView.findViewById(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.noteitem,parent,false)
        return  NoteViewHolder(view)
    }

    override fun getItemCount(): Int =note.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note= note[position]
        holder.titleTextView.text= note.title
        holder.contentTextView.text= note.content

        holder.updateButton.setOnClickListener{
            val intent=Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deletButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note deleted...",Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newNote:List<Note>){
        note= newNote
        notifyDataSetChanged()
    }
}