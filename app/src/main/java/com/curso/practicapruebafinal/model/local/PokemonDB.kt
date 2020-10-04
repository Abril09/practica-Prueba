package com.curso.practicapruebafinal.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.curso.practicapruebafinal.model.remote.Pokemon

private const val DATA_BASE_NAME = "pokemon_db"

@Database(entities = [PokemonEntity::class], version = 4)
abstract class PokemonDB : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDB? = null

        fun getDatabase(context: Context): PokemonDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return  tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    PokemonDB::class.java,
                    DATA_BASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}