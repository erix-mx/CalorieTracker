package com.erix.course.philipp.core_ui.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchEditText(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    backgroundColor: Color = Color.Black,
    textColor: Color = Color.White,
    onTextChanged: (String) -> Unit,
    onClear: () -> Unit = {},
    onSearch: () -> Unit = {},
) {
    TextField(
        modifier = modifier
            .testTag("search_textfield")
            .fillMaxWidth()
            .height(56.dp)
            .clip(shape = CircleShape)
            .background(color = backgroundColor),
        value = text,
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                defaultKeyboardAction(ImeAction.Search)
            }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        onValueChange = onTextChanged,
        placeholder = { Text(placeholder, color = textColor.copy(alpha = 0.5f)) },
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
                    onClick = { onClear() },
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
    SearchEditText(
        text = "Hello World",
        placeholder = "Search",
        onTextChanged = {}
    )
}

@Composable
@Preview
fun SearchBoxPreviewEmpty() {
    SearchEditText(
        text = "",
        placeholder = "Search",
        onTextChanged = {}
    )
}