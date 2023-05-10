object Retrofit {

    object Versions {
        const val retrofit = "2.9.0"
        const val loggerInterceptor = "4.9.3"
        const val mochi = "1.14.0"
    }

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val LOGGER_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.loggerInterceptor}"
    const val MOCHI = "com.squareup.moshi:moshi-kotlin:${Versions.mochi}"
    const val MOCHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val MOCHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.mochi}"
}