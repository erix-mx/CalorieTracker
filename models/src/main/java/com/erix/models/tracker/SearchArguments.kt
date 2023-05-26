package com.erix.models.tracker

data class SearchArguments(
    val mealName: String = "",
    val dayOfMonth: Int = 1,
    val month: Int = 1,
    val year: Int = 1,
)