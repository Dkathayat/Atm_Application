package com.example.atmapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atmapplication.databinding.NotesSavedListitemBinding
import com.example.atmapplication.dto.Notes

class NotesRecylerView(private val myList: ArrayList<Notes>): RecyclerView.Adapter<NotesRecylerView.MyViewHolder>() {
    inner class MyViewHolder(val binding: NotesSavedListitemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    fun updateNotes(newNotes: List<Notes>) {
        myList.clear()
        myList.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(NotesSavedListitemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val note = myList[position]
        holder.binding.apply {
            mainAtmBalanceItem.text = note.amount
            mainAtm100Item.text = note.hundrad
            mainAtm200Item.text = note.twoHundrad
            mainAtm500Item.text = note.fivehundrad
            mainAtm2000Item.text = note.twoThousand
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}