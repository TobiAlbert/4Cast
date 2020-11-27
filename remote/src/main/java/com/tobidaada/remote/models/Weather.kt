package com.tobidaada.remote.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("description")
    val description: String,

    @SerializedName("icon")
    val icon: String
)