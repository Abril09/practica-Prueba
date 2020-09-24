package com.curso.practicapruebafinal.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("list")
    fun getBreedsFromApi() : Call<List<BredAndImages>>

    @GET("breed/{name}/images")
    fun getBreedImageFromApi(@Path("name")name:String):Call<List<BredAndImages>>
}