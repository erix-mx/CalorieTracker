package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.colors.ColorsTrackerBar


@Composable
fun NutrientsBar(
    modifier: Modifier = Modifier,
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    colors: ColorsTrackerBar = ColorsTrackerBar(),
) {

    val carbWidthRatio = remember {
        Animatable(0f)
    }

    val proteinWidthRatio = remember {
        Animatable(0f)
    }

    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = ((protein * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / calorieGoal)
        )
    }

    Canvas(
        modifier = modifier
    ) {
        if (calories <= calorieGoal) {

            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(
                color = colors.background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = colors.fatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = colors.proteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = colors.proteinColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

        } else {
            drawRoundRect(
                color = colors.exceed,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }

}

@Composable
@Preview(widthDp = 411, device = Devices.PIXEL_4_XL)
fun NutrientBarPreview() {

    NutrientsBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp),
        carbs = 50,
        protein = 40,
        fat = 20,
        calories = 1,
        calorieGoal = 70,
    )

}
