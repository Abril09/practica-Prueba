package com.curso.practicapruebafinal.model

import android.util.Log
import com.curso.practicapruebafinal.model.remote.BredAndImages
import com.curso.practicapruebafinal.model.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class Repository {
    private val service = RetrofitClient.getRetrofitClient()

    fun getBreedsFromServer(name:String? = null) {

        val call = if(name.isNullOrEmpty()) service.getBreedsFromApi() else service.getBreedImageFromApi(name)

        call.enqueue(object : Callback<List<BredAndImages>> {

            override fun onResponse(call: Call<List<BredAndImages>>, response: Response<List<BredAndImages>>) {
                when (response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {

                        }
                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<BredAndImages>>, t: Throwable) {
                Log.e("Repository", t.message.toString())
            }

        })

    }

}