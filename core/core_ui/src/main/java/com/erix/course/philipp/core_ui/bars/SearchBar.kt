package com.erix.course.philipp.core_ui.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erix.course.philipp.core_ui.form.SearchEditText
import com.erix.course.philipp.core_ui.theme.SearchBarColors

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    title: String = "",
    onBack: () -> Unit = {},
    onQueryChange: (String) -> Unit,
    colors: SearchBarColors = SearchBarColors(),
    onClear: () -> Unit = {},
    onSearch: () -> Unit = {},
    ) {

    Surface(elevation = 3.dp, ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colors.background),
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            BackBarBigTitle(
                title = title,
                tint = colors.text,
                onClick = onBack,
            )
            SearchEditText(
                modifier = Modifier.padding(horizontal = 21.dp, vertical = 8.dp),
                text = query,
                placeholder = "Search",
                backgroundColor = colors.backgroundBox,
                textColor = colors.text,
                onTextChanged = onQueryChange,
                onClear = onClear,
                onSearch = onSearch,
            )
        }
    }
}


@Composable
@Preview
fun SearchBarPreview() {
    SearchBar(
        title = "Title",
        onBack = {},
        onQueryChange = {},
    )
}