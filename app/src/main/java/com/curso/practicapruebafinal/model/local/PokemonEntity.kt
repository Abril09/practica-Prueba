package com.curso.practicapruebafinal.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.curso.practicapruebafinal.model.remote.AbilityProperties
import com.curso.practicapruebafinal.model.remote.Type
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon_table")
data class PokemonEntity (
    @PrimaryKey val id: String,
    val name: String,
    val image:String,
    val abilities: String,
    val types: String,
    var rank:Double=0.0

)