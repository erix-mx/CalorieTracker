package com.erix.course.philipp.core_ui.dialogs

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.erix.course.philipp.core_ui.LocalSpacing
import com.erix.course.philipp.core_ui.theme.SheetButtonColors


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