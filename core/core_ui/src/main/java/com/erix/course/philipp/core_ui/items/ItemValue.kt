package com.erix.course.philipp.core_ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.divider.ShortDivider
import com.erix.course.philipp.core_ui.fonts.interFontFamily
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.text.H4

@Composable
fun ItemValue(
    modifier: Modifier = Modifier,
    unit: String,
    value: Int,
    color: Color = Color.Black,
    click: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable { click() },
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            H4(
                modifier = Modifier,
                text = value.toString(),
                color = color,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                fontSize = 16.sp,
                fontFamily = interFontFamily,
                text = unit,
                color = color.copy(alpha = 0.6f),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(bottom = 14.dp)

            )
        }
        ShortDivider(color = color)
    }
}

@Composable
@Preview
fun ItemValuePreview() {
    ItemValue(
        unit = "cm",
        value = 180,
    )
}
