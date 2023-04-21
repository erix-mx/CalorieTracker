package com.erix.course.philipp.onboarding_presentation.welcome

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.erix.course.philipp.core_main.R
import com.erix.course.philipp.core_presentation.LocalSpacing

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Text(
        modifier = Modifier.padding(spacing.spaceMedium),
        text = stringResource(id = R.string.module_name),
        fontWeight = FontWeight.Bold,
    )
}


@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreen(onNextClick = {})
}
