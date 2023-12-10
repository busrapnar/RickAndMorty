package com.busrapinar.rickandmorty2.api.models

data class CharacterResponse(
    val info: Info,
    val results: List<ResultCharacter>
)