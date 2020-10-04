package com.curso.practicapruebafinal.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    fun getAllPokemonFromDB(): LiveData<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_table WHERE id =:id")
    fun getPokemonByID(id: String): LiveData<PokemonEntity>

    @Query("Select count(*) from pokemon_table WHERE id =:id")
    fun existPokemon(id: String):Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOnePokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMultiplePokemon(pokemons: List<PokemonEntity>)

    @Update
    suspend  fun updateOnePokemon(poke:PokemonEntity )
}