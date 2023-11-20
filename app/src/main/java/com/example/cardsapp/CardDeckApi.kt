package com.example.cardsapp

import retrofit2.http.GET


public interface CardDeckApi {
    @GET("api/deck/new/draw/?count=54&jokers_enabled=true")
    suspend fun  getAllCards(): Cards
}