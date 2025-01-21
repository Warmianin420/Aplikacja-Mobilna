package com.example.projekt

data class WeatherResponse(
    val name: String,                // Nazwa miasta
    val main: Main,                  // Główne dane pogodowe
    val weather: List<Weather>,      // Lista warunków pogodowych
    val wind: Wind,                  // Dane dotyczące wiatru
    val visibility: Int,             // Widoczność w metrach
    val sys: Sys                     // Dodatkowe dane systemowe
)

data class Main(
    val temp: Float,                 // Aktualna temperatura
    val humidity: Int,               // Wilgotność
    val pressure: Int,               // Ciśnienie atmosferyczne
    val temp_min: Float,             // Minimalna temperatura
    val temp_max: Float              // Maksymalna temperatura
)

data class Weather(
    val description: String          // Opis warunków pogodowych
)

data class Wind(
    val speed: Float,                // Prędkość wiatru
    val deg: Int                     // Kierunek wiatru w stopniach
)

data class Sys(
    val sunrise: Long,               // Czas wschodu słońca (UNIX timestamp)
    val sunset: Long                 // Czas zachodu słońca (UNIX timestamp)
)
