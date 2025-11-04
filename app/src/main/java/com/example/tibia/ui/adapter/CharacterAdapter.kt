package com.example.tibia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tibia.R
import com.example.tibia.model.entity.CharacterDB

class CharacterAdapter(
    private val characters: List<CharacterDB>
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_character, parent, false)) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvLevel: TextView = itemView.findViewById(R.id.tvLevel)
        val tvVocation: TextView = itemView.findViewById(R.id.tvVocation)
        val tvWorld: TextView = itemView.findViewById(R.id.tvWorld)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.tvName.text = character.name
        holder.tvLevel.text = "Level: ${character.level}"
        holder.tvVocation.text = "Vocation: ${character.vocation}"
        holder.tvWorld.text = "World: ${character.world}"
    }

    override fun getItemCount(): Int = characters.size
}
