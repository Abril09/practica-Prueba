package com.curso.practicapruebafinal.model.remote


data class ResponseApi(
    val results: List<Pokemon>,
    val name: String,
    val id: Int,
    val abilities: List<AbilityProperties>,
    val types: List<Type>
)

data class Pokemon(
    val name: String,
    val url: String

)


data class AbilityProperties(
    val ability: Ability

)

data class Type(
    val type: Ability
)


data class Ability(val name: String)