package com.timrashard.weathr.data.model.air

import com.google.gson.annotations.SerializedName
import com.timrashard.weathr.domain.model.EuropeanStandard

data class Components(
    val co: Double,
    val nh3: Double,
    val no: Double,
    val no2: Double, //***
    val o3: Double, //***
    val pm10: Double, //***
    @SerializedName("pm2_5") val pm2_5: Double, //***
    val so2: Double
)

fun Components.toEuropeanStandard(): EuropeanStandard {
    return EuropeanStandard(
        no2 = this.no2,
        pm10 = this.pm10,
        o3 = this.o3,
        pm2_5 = this.pm2_5
    )
}