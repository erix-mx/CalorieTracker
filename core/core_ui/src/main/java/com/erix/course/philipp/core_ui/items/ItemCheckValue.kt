package com.erix.course.philipp.core_ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.R
import com.erix.course.philipp.core_ui.divider.ShortDivider
import com.erix.course.philipp.core_ui.fonts.interFontFamily
import com.erix.course.philipp.core_ui.text.H4

@Composable
fun ItemCheckValue(
    modifier: Modifier = Modifier,
    unit: String,
    value: Int,
    color: Color = Color.Black,
    isChecked: Boolean = false,
    click: (Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable { click(value) },
    ) {


        Column(
            modifier = Modifier.fillMaxSize()
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

        if (isChecked) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.45f),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    tint = color,
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
@Preview
fun ItemCheckValuePreview() {
    ItemCheckValue(
        unit = "cm",
        value = 180,
    )
}

@Composable
@Preview
fun ItemCheckValueIsCheckedPreview() {
    ItemCheckValue(
        unit = "cm",
        value = 180,
        isChecked = true,
    )
}

