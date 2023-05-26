package com.erix.course.philipp.tracker_presentation.search.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.spacers.BoxSpacer
import com.erix.course.philipp.tracker_presentation.R

@Composable
fun WindowPlaceholder(
    @DrawableRes icon: Int = R.drawable.ic_breakfast,
    caption: String = "",
    color: Color = Color.Black
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            tint = color,
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(76.dp)

        )
        BoxSpacer(21.dp)
        Text(
            style = MaterialTheme.typography.headlineSmall,
            color = color,
            text = caption,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun WindowPlaceholder(
    @DrawableRes icon: Int = R.drawable.ic_breakfast,
    caption: String = "",
    blackText: String = "",
    color: Color = Color.Black
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            tint = color,
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)

        )
        BoxSpacer(21.dp)
        Text(
            style = MaterialTheme.typography.headlineSmall,
            color = color,
            text = buildAnnotatedString {
                append(caption)
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = color)) {
                    append(blackText)
                }
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    device = Devices.PIXEL_4_XL,
)
@Composable
fun WindowPlaceholderPreview() {
    WindowPlaceholder(
        icon = R.drawable.ic_breakfast,
        caption = "Add Breakfast"
    )
}

@Preview(
    device = Devices.PIXEL_4_XL,
)
@Composable
fun WindowPlaceholderWithBlackPreview() {
    WindowPlaceholder(
        icon = R.drawable.ic_breakfast,
        caption = "There are no results for ",
        blackText = "Pan duro"
    )
}



