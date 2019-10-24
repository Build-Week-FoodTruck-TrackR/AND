package com.example.foodtruck.data.source.local.model

sealed class City {
    object ChicagoIL : City()
    object BloomingtonIN : City()
    object NewYorkCityNY : City()
    object BostonMA : City()
}