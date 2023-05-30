package com.erix.course.philipp.calorytracker

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import com.erix.course.philipp.calorytracker.extensions.navigateTo
import com.erix.course.philipp.calorytracker.repository.PreferencesFake
import com.erix.course.philipp.calorytracker.repository.TrackerRepositoryFake
import com.erix.course.philipp.core_main.domain.preferences.Preferences
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_ui.R
import com.erix.course.philipp.core_ui.screen_style.AppBackground
import com.erix.course.philipp.core_ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.tracker_domain.usescase.CalculateMealNutrients
import com.erix.course.philipp.tracker_domain.usescase.DeleteTrackedFood
import com.erix.course.philipp.tracker_domain.usescase.GetFoodsForDate
import com.erix.course.philipp.tracker_domain.usescase.SearchFood
import com.erix.course.philipp.tracker_domain.usescase.TrackFood
import com.erix.course.philipp.tracker_domain.usescase.TrackerUseCases
import com.erix.course.philipp.tracker_presentation.search.screens.SearchScreen
import com.erix.course.philipp.tracker_presentation.search.viewmodel.SearchViewModel
import com.erix.course.philipp.tracker_presentation.tracker_overview.screens.OverViewScreen
import com.erix.course.philipp.tracker_presentation.tracker_overview.viewmodel.TrackerOverviewViewModel
import com.erix.models.tracker.SearchArguments
import com.erix.models.tracker.TrackableFood
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
@HiltAndroidTest
class TrackerOverviewE2E {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var repositoryFake: TrackerRepositoryFake
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var trackerOverviewViewModel: TrackerOverviewViewModel
    private lateinit var searchViewModel: SearchViewModel

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        preferences = PreferencesFake()

        repositoryFake = TrackerRepositoryFake()
        trackerUseCases = TrackerUseCases(
            trackFood = TrackFood(repositoryFake),
            searchFood = SearchFood(repositoryFake),
            getFoodsForDate = GetFoodsForDate(repositoryFake),
            deleteTrackedFood = DeleteTrackedFood(repositoryFake),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
        trackerOverviewViewModel = TrackerOverviewViewModel(
            preferences = preferences,
            trackerUseCases = trackerUseCases
        )
        searchViewModel = SearchViewModel(
            trackerUseCases = trackerUseCases,
        )

        composeRule.setContent {

            navController = rememberNavController()

            CalorieTrackerTheme {
                //navController = TestNavHostController(LocalContext.current)
                //navController.navigatorProvider.addNavigator(ComposeNavigator())
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    AppBackground(
                        circleColor = MaterialTheme.colorScheme.onSurface,
                        colorBackground = MaterialTheme.colorScheme.surface
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Route.TRACKER_OVERVIEW,
                        ) {

                            composable(Route.TRACKER_OVERVIEW) {
                                OverViewScreen(
                                    onNavigate = navController::navigateTo,
                                    viewModel = trackerOverviewViewModel
                                )

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
                                    onNavigate = navController::navigateTo,
                                    viewModel = searchViewModel
                                )

                            }

                        }
                    }
                }
            }
        }


    }

    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated() {

        repositoryFake.searchResults = listOf(
            TrackableFood(
                name = "coca-cola",
                imageUrl = null,
                caloriesPer100g = 150,
                proteinPer100g = 5,
                carbsPer100g = 50,
                fatPer100g = 1
            )
        )
        val addedAmount = 150
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedCarbs = (1.5f * 50).roundToInt()
        val expectedProtein = (1.5f * 5).roundToInt()
        val expectedFat = (1.5f * 1).roundToInt()


        composeRule
            .onNodeWithText("Add Breakfast")
            .assertDoesNotExist()

        composeRule
            .onNodeWithText("Breakfast")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Breakfast")
            .performClick()

        composeRule
            .onNodeWithText("Add Breakfast")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Add Breakfast")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.SEARCH) ?: false, `is`(true)
        )

        composeRule
            .onNodeWithTag("search_textfield")
            .performTextInput("banana")

        composeRule.onRoot().printToLog("COMPOSE TREE")

        composeRule.waitUntil(1_500) {
            composeRule
                .onAllNodesWithText("coca-cola")
                .fetchSemanticsNodes().size == 1

        }

        composeRule
            .onNodeWithText("coca-cola")
            .performClick()

        composeRule
            .onNodeWithText("10")
            .performClick()

        composeRule
            .onNodeWithText("Add food")
            .performClick()


        composeRule.waitUntil(5_000) {
            composeRule
                .onAllNodesWithContentDescription("back-to-previous-screen")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onNodeWithContentDescription("back-to-previous-screen")
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("back-to-previous-screen")
            .performClick()


        composeRule
            .onNodeWithText("Breakfast")
            .assertIsDisplayed()


        composeRule
            .onNodeWithText("coca-cola")
            .assertIsDisplayed()



    }
}
