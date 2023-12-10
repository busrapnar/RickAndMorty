package com.busrapinar.rickandmorty2.api.models

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
)