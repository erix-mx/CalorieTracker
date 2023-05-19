

object Hilt {
    const val VERSION = "2.44"

    object ANDROID {
        const val android = "com.google.dagger:hilt-android:$VERSION"
    }

    object COMPILER {
        const val compiler = "com.google.dagger:hilt-android-compiler:$VERSION"
    }

    object Fragment {
        const val version = "1.0.0"
        const val navigation = "androidx.hilt:hilt-navigation-compose:$version"
    }

}