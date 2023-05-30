package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.colors.ColorsTrackerBar
import com.erix.course.philipp.core_ui.colors.ColorsTrackerInfoBar
import com.erix.course.philipp.core_ui.colors.ColorsUnitDisplay
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.theme.CarbColor
import com.erix.course.philipp.core_ui.theme.FatColor
import com.erix.course.philipp.core_ui.theme.ProteinColor
import com.erix.course.philipp.tracker_presentation.R
import com.erix.course.philipp.tracker_presentation.tracker_overview.state.TrackerOverviewState

@Composable
fun NutrientsHeader(
    modifier: Modifier = Modifier,
    state: TrackerOverviewState,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    onClickSettings: () -> Unit = {}
) {

    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories
    )

    val alphaColorBar = 0.5f

    val colorsUnitDisplay = ColorsUnitDisplay(
        amountColor = MaterialTheme.colorScheme.onSecondary,
        unitColor = MaterialTheme.colorScheme.onSecondary
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(backgroundColor)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
        ) {
            Image(
                modifier = Modifier
                    .size(76.dp),
                painter = painterResource(id = R.drawable.ic_avocado_face),
                contentDescription = "Avocado face"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onClickSettings) {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSecondary,
                        painter = painterResource(id = R.drawable.ic_settings_a),
                        contentDescription = null
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(spacing.spaceMedium))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = R.string.kcal),
                colors = ColorsUnitDisplay(
                    amountColor = MaterialTheme.colorScheme.onSecondary,
                    unitColor = MaterialTheme.colorScheme.onSecondary
                ),
                amountTextSize = 32.sp,
                modifier = Modifier.align(Alignment.Bottom)
            )
            Column {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                UnitDisplay(
                    amount = state.caloriesGoal,
                    unit = stringResource(id = R.string.kcal),
                    colors = ColorsUnitDisplay(
                        amountColor = MaterialTheme.colorScheme.onSecondary,
                        unitColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    amountTextSize = 32.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientsBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
            colors = ColorsTrackerBar(
                background = MaterialTheme.colorScheme.onSecondary.copy(alpha = alphaColorBar),
                carbsColor = CarbColor,
                proteinColor = ProteinColor,
                fatColor = FatColor,
                exceed = MaterialTheme.colorScheme.error
            )
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                modifier = Modifier.size(90.dp),
                value = state.totalCarbs,
                goal = state.carbsGoal,
                colors = ColorsTrackerInfoBar(
                    background = MaterialTheme.colorScheme.onSecondary.copy(alpha = alphaColorBar),
                    color = CarbColor
                ),
                name = "Carbs"
            ) {
                UnitDisplay(
                    amount = state.totalCarbs, unit = "g",
                    colors = colorsUnitDisplay,
                )
                Spacer(modifier = Modifier.size(4.dp))
                H3(text = "Carbs", size = 13.sp, color = MaterialTheme.colorScheme.onSecondary)
            }
            NutrientBarInfo(
                modifier = Modifier.size(90.dp),
                value = state.totalProtein,
                goal = state.proteinGoal,
                colors = ColorsTrackerInfoBar(
                    background = MaterialTheme.colorScheme.onSecondary.copy(alpha = alphaColorBar),
                    color = ProteinColor
                ),
                name = "Protein"
            ) {
                UnitDisplay(
                    amount = state.totalProtein, unit = "g",
                    colors = colorsUnitDisplay,
                )
                Spacer(modifier = Modifier.size(4.dp))
                H3(text = "Protein", size = 13.sp, color = MaterialTheme.colorScheme.onSecondary)
            }
            NutrientBarInfo(
                modifier = Modifier.size(90.dp),
                value = state.totalFat,
                goal = state.fatGoal,
                colors = ColorsTrackerInfoBar(
                    background = MaterialTheme.colorScheme.onSecondary.copy(alpha = alphaColorBar),
                    color = FatColor
                ),
                name = "Fats"
            ) {
                UnitDisplay(
                    amount = state.totalFat, unit = "g",
                    colors = colorsUnitDisplay,
                )
                Spacer(modifier = Modifier.size(4.dp))
                H3(text = "Fats", size = 13.sp, color = MaterialTheme.colorScheme.onSecondary)
            }

        }
    }
}

@Composable
@Preview(widthDp = 411)
fun NutrientsHeaderPreview() {

    NutrientsHeader(
        backgroundColor = Color.Black,
        state = TrackerOverviewState(

            totalCarbs = 237,
            totalProtein = 21,
            totalFat = 21,

            carbsGoal = 251,
            proteinGoal = 188,
            fatGoal = 84,

            totalCalories = 1179,
            caloriesGoal = 2510
        )
    )
}
