package com.example.task2.model.data.kotlin

import com.squareup.moshi.Json

data class ViewItem(
    @Json(name = "img") val imageUrl: String,
    @Json(name = "title") val title: String
)
