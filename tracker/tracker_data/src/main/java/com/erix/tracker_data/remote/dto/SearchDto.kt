package com.erix.tracker_data.remote.dto

import com.squareup.moshi.JsonClass

data class SearchDto(
    val products: List<Product>
)
