package com.example.projekt

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String = "en",  // Ustawienie języka, domyślnie angielski
        @Query("units") units: String = "metric" // Ustawienie jednostek, domyślnie metryczne
    ): Call<WeatherResponse>

    @GET("weather")
    fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String = "en",  // Ustawienie języka, domyślnie angielski
        @Query("units") units: String = "metric" // Ustawienie jednostek, domyślnie metryczne
    ): Call<WeatherResponse>
}
