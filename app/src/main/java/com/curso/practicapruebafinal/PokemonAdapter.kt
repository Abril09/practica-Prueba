package com.curso.practicapruebafinal

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.curso.practicapruebafinal.model.local.PokemonEntity
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonAdapter(val callback: CallbackInterface):RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemonList = emptyList<PokemonEntity>()

    fun updateAdapter(mList: List<PokemonEntity>){
        pokemonList = mList
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val itemName = itemView.tv_data
        val itemimage = itemView.imageView
        val rank = itemView.ratingBar2
        val clickLister = itemView.setOnClickListener {
            callback.passTheData(pokemonList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.itemName.text = pokemonList[position].name.toString()
        holder.rank.rating =pokemonList[position].rank.toFloat()
      Glide.with(holder.itemView.context).load(pokemonList[position].image).into(holder.itemimage)
    }

    override fun getItemCount() = pokemonList.size

    interface CallbackInterface {
        fun passTheData(pokemon: PokemonEntity)
    }

}