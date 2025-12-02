package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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

        composeTestRule.onNodeWithText("Nuestras Sucursales").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sucursal Centro").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dirección: Avenida Ficticia 123, Centro").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sucursal Oriente").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sucursal Poniente").assertIsDisplayed()
    }
}