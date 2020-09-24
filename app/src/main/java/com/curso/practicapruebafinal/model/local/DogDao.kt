package com.curso.practicapruebafinal.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.curso.practicapruebafinal.model.local.entity.Dog

@Dao
interface DogDao {

    @Query("SELECT * FROM dog_table")
    fun getAllDogFromDB(): LiveData<List<Dog>>

    @Query("SELECT * FROM dog_table WHERE id =:id")
    fun getDogByID(id: String): LiveData<Dog>

}