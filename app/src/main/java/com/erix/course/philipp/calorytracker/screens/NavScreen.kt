package com.erix.course.philipp.calorytracker.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.calorytracker.ui.theme.CalorieTrackerTheme
import com.erix.course.philipp.core_presentation.LocalSpacing
import com.erix.course.philipp.core_presentation.screen_style.AppBackground
import com.erix.course.philipp.onboarding_presentation.welcome.WelcomeScreen


@Composable
fun NavScreen() {

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppBackground(circleColor = MaterialTheme.colorScheme.onSurface) {
            WelcomeScreen {}
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