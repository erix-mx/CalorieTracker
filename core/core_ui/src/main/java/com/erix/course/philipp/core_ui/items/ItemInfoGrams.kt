package com.erix.course.philipp.core_ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.erix.course.philipp.core_ui.text.H3

@Composable
fun ItemInfoGrams(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            H3(text = "12g")
            H3(text = "Grams")
        }
    }
}

@Composable
@Preview
fun ItemInfoGramsPreview() {
    ItemInfoGrams()
}
