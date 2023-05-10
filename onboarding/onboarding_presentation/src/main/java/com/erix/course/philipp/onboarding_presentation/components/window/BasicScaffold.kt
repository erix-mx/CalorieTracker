package com.erix.course.philipp.onboarding_presentation.components.window

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.bars.BackBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScaffold(
    onNavigate: (UiEvent) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            BackBar(
                tint = MaterialTheme.colorScheme.onSecondary
            ) { onNavigate(UiEvent.NavigateBack) }
        },
    ) { _ ->
        content()
    }
}
