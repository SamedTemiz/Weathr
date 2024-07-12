package com.timrashard.weathr.data.model

data class Alert(
    val description: String,
    val ends: String,
    val endsEpoch: Int,
    val event: String,
    val headline: String,
    val id: String,
    val language: String,
    val link: Any,
    val onset: String,
    val onsetEpoch: Int
)