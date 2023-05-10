package com.erix.course.philipp.core_ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.divider.ShortDivider
import com.erix.course.philipp.core_ui.items.ItemValue
import com.erix.course.philipp.core_ui.text.H1
import com.erix.course.philipp.core_ui.text.H2
import com.erix.course.philipp.core_ui.text.H3

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickerSheetDialog(
    modalState: ModalBottomSheetState,
    values: List<Int>,
    unit: String,
    title: String,
    sheetContentColor: Color = Color.White,
    color: Color = Color.Black,
    onValueSelected: (Int) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val dimensions = LocalSpacing.current
    ModalBottomSheetLayout(
        modifier = Modifier.heightIn(dimensions.maxHeightBottomSheet),
        sheetState = modalState,
        sheetShape = RoundedCornerShape(
            topStart = dimensions.bottomSheetCorner,
            topEnd = dimensions.bottomSheetCorner
        ),
        sheetContentColor = sheetContentColor,
        sheetContent = {
            BodyPickerSheetDialog(
                values = values,
                unit = unit,
                onValueSelected = onValueSelected,
                maxHeight = dimensions.maxHeightBottomSheet,
                title = title,
                color = color,
                sheetContentColor = sheetContentColor,
            )
        }
    ) {
        content()
    }
}


@Composable
fun BodyPickerSheetDialog(
    values: List<Int>,
    unit: String,
    title: String,
    color: Color = Color.Black,
    sheetContentColor: Color = Color.White,
    maxHeight: Dp = 600.dp,
    onValueSelected: (Int) -> Unit = {},
) {

    val dimensions = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxHeight)
            .background(sheetContentColor)
    ) {

        H3(
            size = 21.sp,
            text = title,
            color = color,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.verticalGlobalPadding + 10.dp)
        )

        ShortDivider(color = color)

        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(values) { value ->
                    ItemValue(
                        unit = unit,
                        value = value,
                        color = color,
                        click = { onValueSelected(value) }
                    )
                }
            }
        }

    }
}



@Composable
@Preview(device = Devices.PIXEL_4, showBackground = true, showSystemUi = true)
fun BodyPickerSheetDialogPreview() {
    BodyPickerSheetDialog(
        values = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90),
        unit = "cm",
        title = "Height?",
    )
}



