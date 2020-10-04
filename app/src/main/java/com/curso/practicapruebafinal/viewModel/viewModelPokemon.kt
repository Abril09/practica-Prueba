package com.curso.practicapruebafinal.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.curso.practicapruebafinal.model.Repository
import com.curso.practicapruebafinal.model.local.PokemonDB
import com.curso.practicapruebafinal.model.local.PokemonDao
import com.curso.practicapruebafinal.model.local.PokemonEntity
import com.curso.practicapruebafinal.model.remote.Pokemon
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application) : AndroidViewModel(application){

    private var repository : Repository
    init {
        val pokemonDao = PokemonDB.getDatabase(application).getPokemonDao()
        repository = Repository(pokemonDao)
        repository.getPokemonFromServer()
    }

    fun exposeLiveDataFromDatabase(): LiveData<List<PokemonEntity>> {
        return repository.mLiveData
    }

    fun obtainPokemonByID(id: String): LiveData<PokemonEntity> {
       return  repository.getPokemon(id)
    }

    fun  updatePokemon(pokemonEntity: PokemonEntity)= viewModelScope.launch {
          repository.updatePokemon(pokemonEntity)
    }
}