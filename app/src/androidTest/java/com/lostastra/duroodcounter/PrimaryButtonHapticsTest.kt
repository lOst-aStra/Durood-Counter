package com.lostastra.duroodcounter

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean
import com.lostastra.duroodcounter.ui.components.PrimaryButton

class PrimaryButtonHapticsTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenHapticsEnabled_onHapticIsInvokedOnClick() {
        val hapticCalled = AtomicBoolean(false)

        composeRule.setContent {
            PrimaryButton(
                onClick = {},
                hapticsEnabled = true,
                onHaptic = { hapticCalled.set(true) }
            )
        }

        composeRule.onNodeWithTag("primary-plus1").assertIsDisplayed()
        composeRule.onNodeWithTag("primary-plus1").performClick()

        assert(hapticCalled.get())
    }

    @Test
    fun whenHapticsDisabled_onHapticIsNotInvokedOnClick() {
        val hapticCalled = AtomicBoolean(false)

        composeRule.setContent {
            PrimaryButton(
                onClick = {},
                hapticsEnabled = false,
                onHaptic = { hapticCalled.set(true) }
            )
        }

        composeRule.onNodeWithTag("primary-plus1").assertIsDisplayed()
        composeRule.onNodeWithTag("primary-plus1").performClick()

        assert(!hapticCalled.get())
    }
}
