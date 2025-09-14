package com.lostastra.duroodcounter

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class DuroodScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun plusOneIncrementsCounter() {
        // Initial value should be 0
        composeRule.onNodeWithTag("counter-value").assertExists()
        composeRule.onNodeWithTag("counter-value").assertTextEquals("0")

        // Tap the +1 button
        composeRule.onNodeWithTag("primary-plus1").assertExists()
        composeRule.onNodeWithTag("primary-plus1").performClick()

        // Verify the counter updates to 1
        composeRule.onNodeWithTag("counter-value").assertTextEquals("1")
    }
}
