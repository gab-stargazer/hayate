package com.lelestacia.hayate

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun useAppContext() {
        // Context of the app under test.
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("text:loading-message").assertExists("Loading Text not found")

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("button:search").performClick()

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("anime:5114").performClick()

    }
}