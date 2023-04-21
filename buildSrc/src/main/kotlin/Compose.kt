object Compose {
    object Versions {
        const val BOM = "2023.03.00"
        const val ACTIVITY = "1.7.1"
        const val COMPILER = "1.4.2"
    }

    object Activity {
        const val COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY}"
    }

    object Ui {
        const val COMPOSE_BOM = "androidx.compose:compose-bom:${Versions.BOM}"
        const val UI = "androidx.compose.ui:ui"
        const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        const val MATERIAL3 = "androidx.compose.material3:material3"
    }

    object Test {
        const val JUNIT4 = "androidx.compose.ui:ui-test-junit4"
        const val TOOLING = "androidx.compose.ui:ui-tooling"
        const val MANIFEST = "androidx.compose.ui:ui-test-manifest"
    }

}