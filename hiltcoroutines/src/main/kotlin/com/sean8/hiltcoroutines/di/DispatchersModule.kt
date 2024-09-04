package com.sean8.hiltcoroutines.di

import com.sean8.hiltcoroutines.AppDispatchers
import com.sean8.hiltcoroutines.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Hilt module for providing different coroutine dispatchers.
 *
 * This module is installed in the [SingletonComponent], making the provided dispatchers
 * available as singleton instances throughout the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    /**
     * Provides the [Dispatchers.IO] dispatcher.
     *
     * This dispatcher is optimized for IO-bound operations such as reading or writing to files
     * or databases, and network requests.
     *
     * @return The [Dispatchers.IO] instance.
     */
    @Provides
    @Dispatcher(AppDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * Provides the [Dispatchers.Default] dispatcher.
     *
     * This dispatcher is optimized for CPU-bound operations, such as processing large data sets.
     *
     * @return The [Dispatchers.Default] instance.
     */
    @Provides
    @Dispatcher(AppDispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    /**
     * Provides the [Dispatchers.Main] dispatcher.
     *
     * This dispatcher is optimized for main-thread UI operations.
     *
     * @return The [Dispatchers.Main] instance.
     */
    @Provides
    @Dispatcher(AppDispatchers.Main)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
