package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.colors.ColorsTrackerBar
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.colors.ColorsTrackerInfoBar
import com.erix.course.philipp.core_ui.colors.ColorsUnitDisplay
import com.erix.course.philipp.core_ui.text.H2
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.tracker_presentation.R

@Composable
fun NutrientBarInfo(
    modifier: Modifier = Modifier,
    value: Int,
    goal: Int,
    name: String,
    colors: ColorsTrackerInfoBar = ColorsTrackerInfoBar(),
    strokeWidth: Dp = 6.dp,
    textContent: @Composable () -> Unit = {},
) {

    val background = colors.background
    val goalExceededColor = colors.exceed

    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = value) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        ) {
            drawArc(
                color = if(value <= goal) background else goalExceededColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            if(value <= goal) {
                drawArc(
                    color = colors.color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRatio.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            textContent()
        }
    }

}

@Composable
@Preview(widthDp = 120, heightDp = 120)
fun NutrientBarInfoPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(120.dp),
        contentAlignment = Alignment.Center
    ) {
        NutrientBarInfo(
            modifier = Modifier.size(100.dp),
            value = 50,
            goal = 200,
            name = "Carbs",
        ) {
            UnitDisplay(amount = 100, unit = "g")
            Spacer(modifier = Modifier.size(4.dp))
            H3(text = "Protein", size = 19.sp)
        }
    }
}
