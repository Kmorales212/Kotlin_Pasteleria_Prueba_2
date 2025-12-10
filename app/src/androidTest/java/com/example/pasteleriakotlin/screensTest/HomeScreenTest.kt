package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.example.pasteleriakotlin.ui.screens.HomeScreen
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_muestraTituloYNavegacion() {
        val mockNavController = mockk<NavController>(relaxed = true)

        composeTestRule.setContent {
            HomeScreen(navController = mockNavController)
        }
        composeTestRule.onNodeWithText("Pastelería Kotlin").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Cerrar Sesión").assertIsDisplayed()
        composeTestRule.onNodeWithText("¡Bienvenidos a Pastelería Kotlin!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Inicio").assertIsDisplayed()
        composeTestRule.onNodeWithText("Catálogo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carrito").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contacto").assertIsDisplayed()
    }
}