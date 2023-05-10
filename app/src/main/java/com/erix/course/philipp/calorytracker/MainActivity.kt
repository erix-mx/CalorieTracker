package com.erix.course.philipp.calorytracker

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.erix.course.philipp.calorytracker.screens.NavScreen
import com.erix.course.philipp.calorytracker.ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.calorytracker.ui.theme.avocadoGreenLight
import com.erix.course.philipp.calorytracker.ui.theme.avocadoLighter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalorieTrackerTheme {
                Text(text = "Hello World")
            }
        }
    }
}



