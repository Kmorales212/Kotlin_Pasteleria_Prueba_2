package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo // Importante para listas largas
import androidx.navigation.NavController
import com.example.pasteleriakotlin.ui.screens.ContactoScreen
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class ContactoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun contactoScreen_muestraInformacionDeSucursales() {
        val mockNavController = mockk<NavController>(relaxed = true)

        composeTestRule.setContent {
            ContactoScreen(navController = mockNavController)
        }

        composeTestRule.onNodeWithText("Nuestras Ubicaciones").assertIsDisplayed()

        composeTestRule.onNodeWithText("Sucursal Centro").performScrollTo().assertIsDisplayed()

        composeTestRule.onNodeWithText("Av. Alameda 123", substring = true).performScrollTo().assertIsDisplayed()

        composeTestRule.onNodeWithText("Sucursal Providencia").performScrollTo().assertIsDisplayed()

        composeTestRule.onNodeWithText("Sucursal Las Condes").performScrollTo().assertIsDisplayed()
    }
}