package com.lostastra.duroodcounter

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsEnabled
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

    @Test
    fun plusTenIncrementsByTen() {
        // Ensure starts at 0
        composeRule.onNodeWithTag("counter-value").assertTextEquals("0")
        composeRule.onNodeWithTag("action-plus10").assertExists()
        composeRule.onNodeWithTag("action-plus10").performClick()
        composeRule.onNodeWithTag("counter-value").assertTextEquals("10")
    }

    @Test
    fun plusThirtyThreeIncrementsBy33() {
        // Ensure starts at 0
        composeRule.onNodeWithTag("counter-value").assertTextEquals("0")
        composeRule.onNodeWithTag("action-plus33").assertExists()
        composeRule.onNodeWithTag("action-plus33").performClick()
        composeRule.onNodeWithTag("counter-value").assertTextEquals("33")
    }

    @Test
    fun minusOneDisabledAtZero_andEnabledAfterIncrement() {
        // At start, -1 should be disabled
        composeRule.onNodeWithTag("action-minus1").assertExists()
        composeRule.onNodeWithTag("action-minus1").assertIsNotEnabled()

        // Increment once, then -1 should enable
        composeRule.onNodeWithTag("primary-plus1").performClick()
        composeRule.onNodeWithTag("counter-value").assertTextEquals("1")
        composeRule.onNodeWithTag("action-minus1").assertIsEnabled()

        // Decrement back to 0 and verify disabled again
        composeRule.onNodeWithTag("action-minus1").performClick()
        composeRule.onNodeWithTag("counter-value").assertTextEquals("0")
        composeRule.onNodeWithTag("action-minus1").assertIsNotEnabled()
    }
}
