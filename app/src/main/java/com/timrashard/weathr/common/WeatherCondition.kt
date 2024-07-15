package com.timrashard.weathr.common

import com.timrashard.weathr.R

sealed class WeatherCondition(val condition: String, val icon: Int) {
    data object Drizzle : WeatherCondition("Drizzle", R.drawable.drizzling)
    data object HeavyDrizzle : WeatherCondition("Heavy Drizzle", R.drawable.drizzling)
    data object LightDrizzle : WeatherCondition("Light Drizzle", R.drawable.drizzling)
    data object HeavyDrizzleRain : WeatherCondition("Heavy Drizzle/Rain", R.drawable.rain)
    data object LightDrizzleRain : WeatherCondition("Light Drizzle/Rain", R.drawable.rain)
    data object FreezingDrizzleFreezingRain : WeatherCondition("Freezing Drizzle/Freezing Rain", R.drawable.windy_heavy_rain)
    data object HeavyFreezingDrizzleFreezingRain : WeatherCondition("Heavy Freezing Drizzle/Freezing Rain", R.drawable.drizzling_windy)
    data object LightFreezingDrizzleFreezingRain : WeatherCondition("Light Freezing Drizzle/Freezing Rain", R.drawable.showery_weather)
    data object HeavyFreezingRain : WeatherCondition("Heavy Freezing Rain", R.drawable.heavy_rain_with_lightning)
    data object LightFreezingRain : WeatherCondition("Light Freezing Rain", R.drawable.heavy_rain_with_lightning)
    data object Tornado : WeatherCondition("Funnel Cloud/Tornado", R.drawable.tornado)
    data object PrecipitationInVicinity : WeatherCondition("Precipitation In Vicinity", R.drawable.rain)
    data object Rain : WeatherCondition("Rain", R.drawable.rain)
    data object HeavyRainAndSnow : WeatherCondition("Heavy Rain and Snow", R.drawable.heavy_rain_with_lightning)
    data object LightRainAndSnow : WeatherCondition("Light Rain and Snow", R.drawable.drizzling)
    data object RainShowers : WeatherCondition("Rain Showers", R.drawable.heavy_rain)
    data object HeavyRain : WeatherCondition("Heavy Rain", R.drawable.heavy_rain)
    data object LightRain : WeatherCondition("Light Rain", R.drawable.rain)
    data object Snow : WeatherCondition("Snow", R.drawable.snow)
    data object SnowAndRainShowers : WeatherCondition("Snow and Rain Showers", R.drawable.snowfall)
    data object SnowShowers : WeatherCondition("Snow Showers", R.drawable.snowfall_windy)
    data object HeavySnow : WeatherCondition("Heavy Snow", R.drawable.snowfall)
    data object LightSnow : WeatherCondition("Light Snow", R.drawable.snowfall)
    data object Squalls : WeatherCondition("Squalls", R.drawable.thunderstorm)
    data object Thunderstorm : WeatherCondition("Thunderstorm", R.drawable.thunderstorm)
    data object ThunderstormWithoutPrecipitation : WeatherCondition("Thunderstorm Without Precipitation", R.drawable.lightning_storm)
    data object Overcast : WeatherCondition("Overcast", R.drawable.cloud)
    data object PartiallyCloudy : WeatherCondition("Partially Cloudy", R.drawable.sunny_day)
    data object Clear : WeatherCondition("Clear", R.drawable.sun)

    companion object {
        fun fromString(condition: String): WeatherCondition {
            return when (condition) {
                "Drizzle" -> Drizzle
                "Heavy Drizzle" -> HeavyDrizzle
                "Light Drizzle" -> LightDrizzle
                "Heavy Drizzle/Rain" -> HeavyDrizzleRain
                "Light Drizzle/Rain" -> LightDrizzleRain
                "Freezing Drizzle/Freezing Rain" -> FreezingDrizzleFreezingRain
                "Heavy Freezing Drizzle/Freezing Rain" -> HeavyFreezingDrizzleFreezingRain
                "Light Freezing Drizzle/Freezing Rain" -> LightFreezingDrizzleFreezingRain
                "Heavy Freezing Rain" -> HeavyFreezingRain
                "Light Freezing Rain" -> LightFreezingRain
                "Funnel Cloud/Tornado" -> Tornado
                "Precipitation In Vicinity" -> PrecipitationInVicinity
                "Rain" -> Rain
                "Heavy Rain and Snow" -> HeavyRainAndSnow
                "Light Rain and Snow" -> LightRainAndSnow
                "Rain Showers" -> RainShowers
                "Heavy Rain" -> HeavyRain
                "Light Rain" -> LightRain
                "Snow" -> Snow
                "Snow and Rain Showers" -> SnowAndRainShowers
                "Snow Showers" -> SnowShowers
                else -> throw IllegalArgumentException("Unknown weather condition: $condition")
            }
        }
    }
}

/*
    Diğer Hava Koşulları
        https://docs.google.com/spreadsheets/d/1bHF4QmSr5kMgLP99_BVSpSOj0rwWDeMTK38iNpoIg2w/edit?gid=2102047011#gid=2102047011

    //    data object BlowingOrDriftingSnow : WeatherCondition("Blowing or Drifting Snow", R.drawable.blowing_or_drifting_snow)
    //    data object DustStorm : WeatherCondition("Dust Storm", R.drawable.dust_storm)
    //    data object Fog : WeatherCondition("Fog", R.drawable.fog)
    //    data object FreezingFog : WeatherCondition("Freezing Fog", R.drawable.freezing_fog)
    //    data object HailShowers : WeatherCondition("Hail Showers", R.drawable.hail_showers)
    //    data object Ice : WeatherCondition("Ice", R.drawable.ice)
    //    data object LightningWithoutThunder : WeatherCondition("Lightning Without Thunder", R.drawable.lightning_without_thunder)
    //    data object Mist : WeatherCondition("Mist", R.drawable.mist)
    //    data object SkyCoverageDecreasing : WeatherCondition("Sky Coverage Decreasing", R.drawable.sky_coverage_decreasing)
    //    data object SkyCoverageIncreasing : WeatherCondition("Sky Coverage Increasing", R.drawable.sky_coverage_increasing)
    //    data object SkyUnchanged : WeatherCondition("Sky Unchanged", R.drawable.sky_unchanged)
    //    data object SmokeOrHaze : WeatherCondition("Smoke or Haze", R.drawable.smoke_or_haze)
    //    data object DiamondDust : WeatherCondition("Diamond Dust", R.drawable.diamond_dust)
    //    data object Hail : WeatherCondition("Hail", R.drawable.hail)
 */