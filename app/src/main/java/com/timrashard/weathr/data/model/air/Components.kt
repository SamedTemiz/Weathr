package com.timrashard.weathr.data.model.air

import com.google.gson.annotations.SerializedName

data class Components(
    val co: Double,
    val nh3: Double,
    val no: Double,
    val no2: Double,
    val o3: Double,
    val pm10: Double,
    @SerializedName("pm2_5") val pm2_5: Double,
    val so2: Double
)