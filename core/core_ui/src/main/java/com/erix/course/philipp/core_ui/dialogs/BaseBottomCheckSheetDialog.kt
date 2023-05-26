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
fun PickerCheckSheetDialog(
    modalState: ModalBottomSheetState,
    values: List<Int>,
    unit: String,
    title: String,
    selectedValueAmount: Int = -1,
    sheetContentColor: Color = Color.White,
    color: Color = Color.Black,
    onValueSelected: (Int) -> Unit = {},
    click: () -> Unit = {},
    content: @Composable () -> Unit = {},
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
            PickerCheckBottomDialog(
                selectedValueAmount = selectedValueAmount,
                values = values,
                unit = unit,
                onValueSelected = onValueSelected,
                maxHeight = dimensions.maxHeightBottomSheet,
                title = title,
                color = color,
                click = click,
                sheetContentColor = sheetContentColor,
                buttonColors = if (selectedValueAmount != -1) {
                    SheetButtonColors(
                        background = MaterialTheme.colorScheme.primary,
                        text = MaterialTheme.colorScheme.secondary,
                    )
                } else {
                    SheetButtonColors(
                        background = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.2f),
                        text = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.6f),
                    )
                }
            )
        }
    ) {
        content()
    }
}