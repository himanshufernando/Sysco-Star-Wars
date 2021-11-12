package com.example.syscostarwars.data

data class Planet(
    var count: Int,
    var next: String,
    var previous: Any,
    var results: List<Result>
)