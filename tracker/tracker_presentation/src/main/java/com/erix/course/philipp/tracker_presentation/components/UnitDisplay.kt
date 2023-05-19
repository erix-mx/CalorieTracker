package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.colors.ColorsUnitDisplay

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    amount: Int,
    unit: String,
    amountTextSize: TextUnit = 20.sp,
    colors: ColorsUnitDisplay = ColorsUnitDisplay(),
    unitTextSize: TextUnit = 14.sp,
) {
    val spacing = LocalSpacing.current
    Row(modifier = modifier) {
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = amountTextSize,
            color = colors.amountColor,
            modifier = Modifier.alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
        Text(
            text = unit,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = unitTextSize,
            color = colors.unitColor,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}

@Composable
@Preview
fun UnitDisplayPreview() {
    UnitDisplay(amount = 100, unit = "g")
}