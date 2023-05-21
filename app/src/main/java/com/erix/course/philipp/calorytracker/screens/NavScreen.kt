package com.erix.course.philipp.calorytracker.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.erix.course.philipp.calorytracker.extensions.navigateTo
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_ui.screen_style.AppBackground
import com.erix.course.philipp.onboarding_presentation.activity.ActivityScreen
import com.erix.course.philipp.onboarding_presentation.age.AgeScreen
import com.erix.course.philipp.onboarding_presentation.gender.GenderScreen
import com.erix.course.philipp.onboarding_presentation.goal.GoalScreen
import com.erix.course.philipp.onboarding_presentation.height.HeightScreen
import com.erix.course.philipp.onboarding_presentation.nutrient.NutrientScreen
import com.erix.course.philipp.onboarding_presentation.weight.WeightScreen
import com.erix.course.philipp.onboarding_presentation.welcome.WelcomeScreen


@Composable
fun NavScreen() {
        
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppBackground(
            circleColor = MaterialTheme.colorScheme.onSurface,
            colorBackground = MaterialTheme.colorScheme.surface
        ) {

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Route.WELCOME,

                ) {
                composable(Route.WELCOME) {
                    WelcomeScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.GENDER) {
                    GenderScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.AGE) {
                    AgeScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.HEIGHT) {
                    HeightScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.WEIGHT) {
                    WeightScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.ACTIVITY) {
                    ActivityScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.GOAL) {
                    GoalScreen(onNavigate = navController::navigateTo)
                }
                composable(Route.NUTRIENT_GOAL) {
                    NutrientScreen(onNavigate = navController::navigateTo)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun NavScreenPreview() {
    CalorieTrackerTheme {
        AppBackground {
            NavScreen()
        }
    }
}