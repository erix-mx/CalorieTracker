package com.erix.course.philipp.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.erix.course.philipp.calorytracker.ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.tracker_presentation.tracker_overview.screens.OverViewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalorieTrackerTheme {

                OverViewScreen()

            }
        }
    }
}



