package com.timrashard.weathr.utils

import android.content.Context
import com.timrashard.weathr.R

class MathUtils(private val context: Context) {

    fun calculateOppositeAngle(angle: Float): Float {
        return (angle + 180) % 360
    }

    fun getAngleDirection(angle: Float): String {
        return when ((angle % 360 + 360) % 360) {  // Ensure the angle is between 0 and 360
            in 0.0..22.5, in 337.5..360.0 -> context.getString(R.string.north)
            in 22.5..67.5 -> context.getString(R.string.north_east)
            in 67.5..112.5 -> context.getString(R.string.east)
            in 112.5..157.5 -> context.getString(R.string.south_east)
            in 157.5..202.5 -> context.getString(R.string.south)
            in 202.5..247.5 -> context.getString(R.string.south_west)
            in 247.5..292.5 -> context.getString(R.string.west)
            in 292.5..337.5 -> context.getString(R.string.north_west)
            else -> "Unknown"
        }
    }
}