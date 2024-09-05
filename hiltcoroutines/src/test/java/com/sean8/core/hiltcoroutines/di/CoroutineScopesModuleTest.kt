package com.sean8.core.hiltcoroutines.di

import com.sean8.core.hiltcoroutines.AppDispatchers
import com.sean8.core.hiltcoroutines.Dispatcher
import com.sean8.core.hiltcoroutines.di.ApplicationScope
import com.sean8.core.hiltcoroutines.di.IOScope
import com.sean8.core.hiltcoroutines.di.MainScope
import com.sean8.core.hiltcoroutines.di.StrictScope
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.coroutines.ContinuationInterceptor

@ExperimentalCoroutinesApi
@HiltAndroidTest
@Config(manifest=Config.NONE, application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class CoroutineScopesModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @ApplicationScope
    lateinit var appScope: CoroutineScope

    @Inject
    @IOScope
    lateinit var ioScope: CoroutineScope

    @Inject
    @MainScope
    lateinit var mainScope: CoroutineScope

    @Inject
    @StrictScope
    lateinit var strictScope: CoroutineScope

    @Inject
    @Dispatcher(AppDispatchers.Default)
    lateinit var defaultDispatcher: CoroutineDispatcher

    @Inject
    @Dispatcher(AppDispatchers.IO)
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    @Dispatcher(AppDispatchers.Main)
    lateinit var mainDispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testScopesUseCorrectDispatchers() {
        assertEquals(defaultDispatcher, appScope.coroutineContext[ContinuationInterceptor])
        assertEquals(ioDispatcher, ioScope.coroutineContext[ContinuationInterceptor])
        assertEquals(mainDispatcher, mainScope.coroutineContext[ContinuationInterceptor])
        assertEquals(defaultDispatcher, strictScope.coroutineContext[ContinuationInterceptor])
    }
}
