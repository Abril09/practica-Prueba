package com.curso.practicapruebafinal.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.curso.practicapruebafinal.model.local.PokemonDao
import com.curso.practicapruebafinal.model.local.PokemonEntity
import com.curso.practicapruebafinal.model.remote.Pokemon
import com.curso.practicapruebafinal.model.remote.ResponseApi
import com.curso.practicapruebafinal.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val pokemon: PokemonDao) {
    private val service = RetrofitClient.getRetrofitClient()
    val mLiveData = pokemon.getAllPokemonFromDB()

    fun getPokemon(id: String): LiveData<PokemonEntity> {
        return pokemon.getPokemonByID(id)
    }

    suspend fun updatePokemon(pokemonEntity: PokemonEntity){
        pokemon.updateOnePokemon(pokemonEntity)
    }

    fun getPokemonFromServer() {
        val call = service.getPokemonFromApi()

        call.enqueue(object : Callback<ResponseApi> {

            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                when (response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            for (item: Pokemon in response.body()!!.results) {

                                pokemonGetAbilities(item.name)
                            }
                        }
                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }

        })

    }


    fun pokemonGetAbilities(name: String) {
        val call = service.getPokemonFromAmiAbilities(name)
        call.enqueue(object : Callback<ResponseApi> {

            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                when (response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            Log.d("ver", pokemon.getPokemonByID(it.id.toString()).toString())
                            if(pokemon.existPokemon(it.id.toString())<1){
                                pokemon.insertOnePokemon(
                                    PokemonEntity(
                                        name = it.name,
                                        id = it.id.toString(),
                                        image = "https://pokeres.bastionbot.org/images/pokemon/" + it.id.toString() + ".png",
                                        abilities = cleanData(it.abilities.map { x -> x.ability.name }
                                            .toString()),
                                        types = cleanData(it.types.map { x -> x.type.name }.toString()),

                                        )
                                )
                            }

                        }
                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }

        })
    }

    fun cleanData(data: String): String {
        val retorno = data.subSequence(1, data.length-1).toString()
        Log.d("ret",retorno)
        return retorno
    }

}