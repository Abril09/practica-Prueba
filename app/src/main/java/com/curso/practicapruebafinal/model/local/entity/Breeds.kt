package com.curso.practicapruebafinal.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds_table")
data class Breeds (
    @PrimaryKey val id: String,
    val name: String
)