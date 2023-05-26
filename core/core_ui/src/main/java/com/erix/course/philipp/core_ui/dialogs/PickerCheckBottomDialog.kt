package com.erix.course.philipp.core_ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.R
import com.erix.course.philipp.core_ui.buttons.BigButton
import com.erix.course.philipp.core_ui.divider.ShortDivider
import com.erix.course.philipp.core_ui.items.ItemCheckValue
import com.erix.course.philipp.core_ui.text.H3
import com.erix.course.philipp.core_ui.text.Normal
import com.erix.course.philipp.core_ui.theme.SheetButtonColors

@Composable
fun PickerCheckBottomDialog(
    values: List<Int>,
    unit: String,
    title: String,
    color: Color = Color.Black,
    sheetContentColor: Color = Color.White,
    maxHeight: Dp = 600.dp,
    selectedValueAmount: Int = -1,
    buttonColors: SheetButtonColors = SheetButtonColors(),
    onValueSelected: (Int) -> Unit = {},
    click: () -> Unit = {},

) {
    val dimensions = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxHeight)
            .background(sheetContentColor)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            H3(
                size = 21.sp,
                text = title,
                color = color,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensions.verticalGlobalPadding
                    )
                    .padding(top = dimensions.verticalGlobalPadding)
            )
            Normal(
                text = stringResource(id = R.string.tag_subtitle_picker_check),
                color = color,
                modifier = Modifier.padding(
                    vertical = 10.dp,
                )
            )
        }


        ShortDivider(color = color)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(values) { value ->
                    ItemCheckValue(
                        unit = unit,
                        value = value,
                        color = color,
                        isChecked = (selectedValueAmount == value),
                        click = {
                            onValueSelected(it)
                        }
                    )
                }
            }
        }
        ShortDivider(color = color)
        
        Box(modifier = Modifier.padding(horizontal = dimensions.verticalGlobalPadding, vertical = 10.dp)) {
            BigButton(
                text = stringResource(id = R.string.tag_add_food),
                iconRes = R.drawable.ic_plus,
                backgroundColor = buttonColors.background,
                textColor = buttonColors.text,
            ) { if (selectedValueAmount != -1) click() }
        }


    }
}

@Preview
@Composable
fun PickerCheckBottomDialogPreview() {
    PickerCheckBottomDialog(
        values = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90),
        unit = "g",
        title = "Pan de muerto con choricito extra caliente",
    )
}