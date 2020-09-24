package com.curso.practicapruebafinal.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dog_table")
data class Dog(
               @PrimaryKey val id: String,
               val type: String,
               @SerializedName("img_src")val imgSrc: String)