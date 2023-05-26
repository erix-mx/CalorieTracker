package com.erix.course.philipp.calorytracker.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erix.course.philipp.calorytracker.extensions.navigateTo
import com.erix.course.philipp.calorytracker.viewmodel.MainViewModel
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_ui.screen_style.AppBackground
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.onboarding_presentation.activity.ActivityScreen
import com.erix.course.philipp.onboarding_presentation.age.AgeScreen
import com.erix.course.philipp.onboarding_presentation.gender.GenderScreen
import com.erix.course.philipp.onboarding_presentation.goal.GoalScreen
import com.erix.course.philipp.onboarding_presentation.height.HeightScreen
import com.erix.course.philipp.onboarding_presentation.nutrient.NutrientScreen
import com.erix.course.philipp.onboarding_presentation.weight.WeightScreen
import com.erix.course.philipp.onboarding_presentation.welcome.WelcomeScreen
import com.erix.course.philipp.tracker_presentation.search.screens.SearchScreen
import com.erix.course.philipp.tracker_presentation.tracker_overview.screens.OverViewScreen
import com.erix.models.tracker.SearchArguments


@Composable
fun NavScreen(
    viewModel: MainViewModel = hiltViewModel(),
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppBackground(
            circleColor = MaterialTheme.colorScheme.onSurface,
            colorBackground = MaterialTheme.colorScheme.surface
        ) {


            val startDestination = if (viewModel.state.showOnBoarding) {
                Route.WELCOME
            } else {
                Route.TRACKER_OVERVIEW
            }

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = startDestination,
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
                composable(Route.TRACKER_OVERVIEW) {
                    OverViewScreen(onNavigate = navController::navigateTo)
                }
                composable(
                    Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                    arguments = listOf(
                        navArgument("mealName") {
                            type = NavType.StringType
                        },
                        navArgument("dayOfMonth") {
                            type = NavType.IntType
                        },
                        navArgument("month") {
                            type = NavType.IntType
                        },
                        navArgument("year") {
                            type = NavType.IntType
                        },
                    )
                ) {
                    val searchArguments = SearchArguments(
                        mealName = it.arguments?.getString("mealName") ?: "",
                        dayOfMonth = it.arguments?.getInt("dayOfMonth") ?: 1,
                        month = it.arguments?.getInt("month") ?: 1,
                        year = it.arguments?.getInt("year") ?: 1,
                    )

                    SearchScreen(
                        searchArguments = searchArguments,
                        onNavigate = navController::navigateTo
                    )

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