package com.sean8.hiltcoroutines

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * Qualifier annotation for specifying different Coroutine Dispatchers.
 *
 * This is used to distinguish between the different [AppDispatchers] provided
 * by the dependency injection framework using Hilt.
 *
 * @property appDispatcher The dispatcher type from [AppDispatchers].
 */
@Suppress("unused")
@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val appDispatcher: AppDispatchers)

/**
 * Enum representing the available coroutine dispatchers in the application.
 *
 * - [Default] corresponds to [Dispatchers.Default].
 * - [IO] corresponds to [Dispatchers.IO].
 * - [Main] corresponds to [Dispatchers.Main].
 */
enum class AppDispatchers {
    Default,
    IO,
    Main,
}
