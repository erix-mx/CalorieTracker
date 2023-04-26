package com.erix.course.philipp.onboarding_presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.erix.course.philipp.onboarding_presentation.R
import com.erix.course.philipp.core_presentation.LocalSpacing
import com.erix.course.philipp.core_presentation.fonts.interFontFamily

@Composable
fun WelcomeScreen(
    onNextClick: () -> Unit,
) {

    val dimensions = LocalSpacing.current

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    Column(
        modifier = Modifier.padding(horizontal = dimensions.verticalScreenPadding)
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
        Text(
            text = stringResource(id = R.string.welcome_title),
            fontWeight = FontWeight.Bold,
            fontFamily = interFontFamily,
            fontSize = 40.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = Modifier.padding(dimensions.spaceMedium))
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.welcome_subtitle))
                append(" ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = R.string.welcome_subtitle_bold))
                }
            },
            fontWeight = FontWeight.Normal,
            fontFamily = interFontFamily,
            fontSize = 23.sp,
            modifier = Modifier.fillMaxWidth().padding(horizontal = dimensions.verticalScreenPadding),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.spaceExtraLargest)
            .background(color = MaterialTheme.colorScheme.primary)
        )
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun WelcomeScreenPreview() {
    WelcomeScreen(onNextClick = {})
}
