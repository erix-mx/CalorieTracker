package com.erix.course.philipp.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.erix.course.philipp.core_main.navigation.Route
import com.erix.course.philipp.core_main.utils.UiEvent
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.text.H1
import com.erix.course.philipp.core_ui.text.H2
import com.erix.course.philipp.onboarding_presentation.R

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent) -> Unit,
) {

    val dimensions = LocalSpacing.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = Modifier.padding(horizontal = dimensions.horizontalGlobalPadding)
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
        H1(
            text = stringResource(id = R.string.welcome_title),
            color = MaterialTheme.colorScheme.onSecondary,
        )

        Spacer(modifier = Modifier.padding(dimensions.spaceMedium))
        H2(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.welcome_subtitle))
                append(" ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = R.string.welcome_subtitle_bold))
                }
            },
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensions.spaceExtraLargest)
        ) {
            BigButton(
                text = stringResource(id = R.string.welcome_button),
                backgroundColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.secondary,
            ) { onNavigate(UiEvent.NavigateTo(Route.GENDER)) }
        }
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun WelcomeScreenPreview() {
    WelcomeScreen {}
}
