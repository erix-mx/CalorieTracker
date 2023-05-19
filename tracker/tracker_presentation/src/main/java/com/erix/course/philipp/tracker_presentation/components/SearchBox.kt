package com.erix.course.philipp.tracker_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.tracker_presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    backgroundColor: Color = Color.Black,
    textColor: Color = Color.White,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .height(56.dp)
            .clip(shape = CircleShape)
            .background(color = backgroundColor),
        value = text,
        onValueChange = onTextChanged,
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColor,
            textColor = textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(
                    onClick = { onTextChanged("") },
                    content = {
                        Icon(
                            modifier = Modifier.padding(10.dp),
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Clear",
                            tint = textColor
                        )
                    }
                )
            }
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_square_search),
                contentDescription = "Search",
                tint = textColor
            )
        }

    )

}


@Composable
@Preview
fun SearchBoxPreview() {
    SearchBox(
        text = "Hello World",
        placeholder = "Search",
        onTextChanged = {}
    )
}

@Composable
@Preview
fun SearchBoxPreviewEmpty() {
    SearchBox(
        text = "",
        placeholder = "Search",
        onTextChanged = {}
    )
}