package com.erix.course.philipp.calorytracker.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.erix.course.philipp.core_main.utils.log
import com.erix.course.philipp.core_presentation.Dimensions
import com.erix.course.philipp.core_presentation.LocalSpacing
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(

    primary = avocadoGreen,
    secondary = avocadoGreenLight,
    tertiary = avocadoBlue,
    background = Color.Yellow,
    onSecondary = avocadoDark,
)

private val LightColorScheme = lightColorScheme(

    primary = avocadoGreen,
    secondary = avocadoGreenLight,
    tertiary = avocadoBlue,
    background = avocadoLighter,
    surface = avocadoLighter,
    onSurface = avocadoGreenLight,
    onSecondary = avocadoDark,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CalorieTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            log { "Using dynamic color scheme" }
            val context = LocalContext.current
            //if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context) //TODO: Add support for dynamic color scheme
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> {
            log { "Using darktheme" }
            DarkColorScheme
        }

        else -> {
            log { "Using lighttheme" }
            LightColorScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = avocadoLighter.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = avocadoGreenLight,
        darkIcons = !darkTheme
    )

    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }


}