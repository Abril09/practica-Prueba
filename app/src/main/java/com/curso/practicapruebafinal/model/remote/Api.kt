package com.curso.practicapruebafinal.model.remote

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface Api {

    @GET("pokemon")
    fun getPokemonFromApi(): Call<ResponseApi>

    @GET("pokemon/{name}")
    fun getPokemonFromAmiAbilities(@Path("name") name:String): Call<ResponseApi>
}