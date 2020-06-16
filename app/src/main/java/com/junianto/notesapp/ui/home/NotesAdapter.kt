package com.junianto.notesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.junianto.notesapp.R
import com.junianto.notesapp.data.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    lateinit var navController: NavController
    inner class NotesViewHolder( itemView: View ): RecyclerView.ViewHolder( itemView )

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.let {
                it.title == newItem.title && it.description == newItem.description
            }
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val curNotesItem = differ.currentList[position]

        holder.itemView.apply {
            tvTitle.text = curNotesItem.title
            tvContent.text = curNotesItem.description
            tvId.text = curNotesItem.id.toString()
            tvTime.text = curNotesItem.timeStamp
        }

        holder.itemView.setOnClickListener {
            val args = Bundle()
            args.putString("title", curNotesItem.title)
            args.putString("description", curNotesItem.description)
            args.putInt("id", curNotesItem.id)
            navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_homeFragment_to_editFragment, args)
        }
    }
}