package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.colors.ColorsButton
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.tracker_presentation.R
import java.time.LocalDate

@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onPreviousDayClick: () -> Unit,
    onNextDayClick: () -> Unit,
    color: Color = Color.White,
    colors: ColorsButton = ColorsButton(),
    size: Dp = 42.dp,
    ) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(colors.background)
                .size(size),
            onClick = onPreviousDayClick) {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                tint = colors.text,
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron),
                contentDescription = stringResource(id = R.string.previous_day)
            )
        }
        H3(
            size = 23.sp,
            modifier = Modifier.wrapContentWidth(),
            text = parseDateText(date = date),
            color = color,
            isMaxWidth = false,
        )
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(colors.background)
                .size(size),
            onClick = onNextDayClick) {
            Icon(
                tint = colors.text,
                modifier = Modifier
                    .size(16.dp)
                    .rotate(degrees = 180f),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron),
                contentDescription = stringResource(id = R.string.next_day)
            )
        }
    }
}


@Composable
@Preview
fun DaySelectorPreview() {
    DaySelector(
        date = LocalDate.now(),
        onPreviousDayClick = {},
        onNextDayClick = {},
    )
}

