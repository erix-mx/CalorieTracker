package com.erix.course.philipp.calorytracker.state

import com.erix.course.philipp.core_main.navigation.Route

data class MainState(
    val mainRoute: String = Route.WELCOME,
    val showOnBoarding: Boolean = true
)