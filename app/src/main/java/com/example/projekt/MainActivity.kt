package com.example.projekt

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val apiKey = "e15ac4e1b0d2fe2ba7240da39bfea1a6" // Klucz API
    private var selectedCity: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCheckWeather = findViewById<Button>(R.id.btnCheckWeather)
        val btnWeatherByLocation = findViewById<Button>(R.id.btnGetWeatherByLocation)
        val spinnerCity = findViewById<Spinner>(R.id.spinnerCity)
        val txtWeather = findViewById<TextView>(R.id.txtWeatherResult)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApiService::class.java)
        
        // Obsługa wyboru miasta z rozwijanej listy
        spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCity = parent.getItemAtPosition(position).toString()
                Log.d("DEBUG", "Selected city: $selectedCity")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCity = ""
            }
        }

        // Obsługa przycisku "Sprawdź pogodę dla wybranego miasta"
        btnCheckWeather.setOnClickListener {
            if (selectedCity.isNotEmpty()) {
                val call = weatherApi.getWeatherByCity(
                    selectedCity, apiKey, "pl", "metric"
                )
                call.enqueue(object : Callback<WeatherResponse> {
                    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                        if (response.isSuccessful) {
                            val weather = response.body()
                            txtWeather.text = """
                                Miasto: ${weather?.name}
                                Temperatura: ${weather?.main?.temp}°C
                                Wilgotność: ${weather?.main?.humidity}%
                                Ciśnienie: ${weather?.main?.pressure} hPa
                                Prędkość wiatru: ${weather?.wind?.speed} m/s
                                Kierunek wiatru: ${weather?.wind?.deg}°
                                Widoczność: ${weather?.visibility} m
                                Opis: ${weather?.weather?.joinToString { it.description }}
                            """.trimIndent()
                        } else {
                            txtWeather.text = "Nie udało się pobrać danych o pogodzie."
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        txtWeather.text = "Błąd: ${t.message}"
                    }
                })
            } else {
                txtWeather.text = "Proszę wybrać miasto z listy."
            }
        }

        // Obsługa przycisku "Pogoda dla mojej lokalizacji"
        btnWeatherByLocation.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    100
                )
                return@setOnClickListener
            }

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                Log.d("DEBUG", "Czy lokalizacja jest dostępna: ${location?.latitude}, ${location?.longitude}")
                if (location != null) {
                    val call = weatherApi.getWeatherByCoordinates(
                        location.latitude,
                        location.longitude,
                        apiKey,
                        "pl", // Język na polski
                        "metric" // Jednostki: stopnie Celsjusza
                    )
                    call.enqueue(object : Callback<WeatherResponse> {
                        override fun onResponse(
                            call: Call<WeatherResponse>,
                            response: Response<WeatherResponse>
                        ) {
                            if (response.isSuccessful) {
                                val weather = response.body()
                                txtWeather.text = """
                                    Miasto: ${weather?.name}
                                    Temperatura: ${weather?.main?.temp}°C
                                    Wilgotność: ${weather?.main?.humidity}%
                                    Ciśnienie: ${weather?.main?.pressure} hPa
                                    Prędkość wiatru: ${weather?.wind?.speed} m/s
                                    Kierunek wiatru: ${weather?.wind?.deg}°
                                    Widoczność: ${weather?.visibility} m
                                    Opis: ${weather?.weather?.joinToString { it.description }}
                                """.trimIndent()
                            } else {
                                txtWeather.text = "Nie udało się pobrać danych o pogodzie."
                            }
                        }

                        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                            txtWeather.text = "Błąd: ${t.message}"
                        }
                    })
                } else {
                    txtWeather.text = "Nie można uzyskać lokalizacji. Spróbuj ponownie."
                }
            }
        }
    }
}
