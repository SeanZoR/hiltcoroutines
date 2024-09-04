package com.sean8.hiltcoroutines.di

import com.sean8.hiltcoroutines.AppDispatchers
import com.sean8.hiltcoroutines.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Qualifier annotation for distinguishing the application's [CoroutineScope].
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

/**
 * Qualifier annotation for distinguishing the IO [CoroutineScope].
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IOScope

/**
 * Qualifier annotation for distinguishing the Main [CoroutineScope].
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainScope

/**
 * Qualifier annotation for a strict [CoroutineScope] using [Job].
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class StrictScope

/**
 * Hilt module for providing different coroutine scopes.
 *
 * This module provides various CoroutineScopes based on the provided [CoroutineDispatcher]
 * and whether a [SupervisorJob] or [Job] is used.
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopesModule {

    /**
     * Provides the application-wide [CoroutineScope] with a [SupervisorJob].
     *
     * This scope uses the [AppDispatchers.Default] dispatcher for CPU-bound tasks.
     *
     * @param dispatcher The [CoroutineDispatcher] to be used for the application scope.
     * @return The application-wide [CoroutineScope].
     */
    @Provides
    @Singleton
    @ApplicationScope
    fun providesApplicationScope(
        @Dispatcher(AppDispatchers.Default) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

    /**
     * Provides the IO-bound [CoroutineScope] with a [SupervisorJob].
     *
     * This scope uses the [AppDispatchers.IO] dispatcher for IO-bound tasks.
     *
     * @param dispatcher The [CoroutineDispatcher] to be used for the IO scope.
     * @return The IO-bound [CoroutineScope].
     */
    @Provides
    @Singleton
    @IOScope
    fun providesIOScope(
        @Dispatcher(AppDispatchers.IO) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

    /**
     * Provides the Main-thread [CoroutineScope] with a [SupervisorJob].
     *
     * This scope uses the [AppDispatchers.Main] dispatcher for UI-bound tasks.
     *
     * @param dispatcher The [CoroutineDispatcher] to be used for the Main scope.
     * @return The Main-thread [CoroutineScope].
     */
    @Provides
    @Singleton
    @MainScope
    fun providesMainScope(
        @Dispatcher(AppDispatchers.Main) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

    /**
     * Provides a strict [CoroutineScope] with a [Job], without the benefits of a [SupervisorJob].
     *
     * This scope uses the [AppDispatchers.Default] dispatcher and adheres to stricter cancellation
     * behavior.
     *
     * @param dispatcher The [CoroutineDispatcher] to be used for the strict scope.
     * @return The strict [CoroutineScope].
     */
    @Provides
    @Singleton
    @StrictScope
    fun providesStrictScope(
        @Dispatcher(AppDispatchers.Default) dispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(Job() + dispatcher)
}