package com.github.seanzor.hiltcoroutines.di

import com.github.seanzor.hiltcoroutines.AppDispatchers
import com.github.seanzor.hiltcoroutines.Dispatcher
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(manifest=Config.NONE, application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class DispatchersModuleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Dispatcher(AppDispatchers.IO)
    lateinit var ioDispatcher: CoroutineDispatcher

    @Inject
    @Dispatcher(AppDispatchers.Default)
    lateinit var defaultDispatcher: CoroutineDispatcher

    @Inject
    @Dispatcher(AppDispatchers.Main)
    lateinit var mainDispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testProvidesIODispatcher() {
        assertEquals(Dispatchers.IO, ioDispatcher)
    }

    @Test
    fun testProvidesDefaultDispatcher() {
        assertEquals(Dispatchers.Default, defaultDispatcher)
    }

    @Test
    fun testProvidesMainDispatcher() {
        assertEquals(Dispatchers.Main, mainDispatcher)
    }
}