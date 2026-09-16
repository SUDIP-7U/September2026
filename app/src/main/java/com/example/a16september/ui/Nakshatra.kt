package com.example.a16september.ui

import android.content.Context
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Pada(
    val number: Int,
    @SerialName("degree_range")
    val degreeRange: String
)

@Serializable
data class Qualities(
    val career: String,
    val health: String,
    val relationship: String
)

@Serializable
data class Nakshatra(
    val id: Int,
    val name: String,
    @SerialName("start_degree")
    val startDegree: Double,
    @SerialName("end_degree")
    val endDegree: Double,
    val ruler: String,
    val deity: String,
    val symbol: String,
    val guna: String,
    val element: String,
    val pada: List<Pada>,
    val qualities: Qualities,
    val compatibility: List<String>,
    val description: String
)

private val jsonInstance = Json { ignoreUnknownKeys = true }

fun loadNakshatras(context: Context): List<Nakshatra> {
    return try {
        val json = context.assets.open("nakshatras.json")
            .bufferedReader().use { it.readText() }
        jsonInstance.decodeFromString(json)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}
