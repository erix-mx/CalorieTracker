package com.erix.course.philipp.core_ui.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.fonts.interFontFamily
import com.erix.course.philipp.core_ui.R

@Composable
fun BigButton(
    modifier: Modifier = Modifier,
    text: String = "Hello!",
    backgroundColor: Color = Color.Black,
    textColor: Color = Color.White,
    @DrawableRes iconRes: Int? = R.drawable.ic_arrow_next,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
        ),
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = textColor,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = interFontFamily,
                fontSize = 19.sp

            )

            iconRes?.let {
                Icon(
                    tint = textColor,
                    painter = painterResource(id = it),
                    contentDescription = stringResource(id = R.string.tag_description_big_button))
            }

        }
    }
}

@Composable
@Preview
fun BigButtonPreview() {
    BigButton(
        text = "Hello!",
        backgroundColor = Color.Black,
        textColor = Color.White,
        iconRes = R.drawable.ic_arrow_next
    )
}
