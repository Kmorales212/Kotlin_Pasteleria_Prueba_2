package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import com.example.pasteleriakotlin.ui.viewModel.AuthViewModel
import com.example.pasteleriakotlin.ui.screens.RegistroScreen
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class RegistroScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registroScreen_muestraElementosCorrectamente() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<AuthViewModel>(relaxed = true)

        composeTestRule.setContent {
            RegistroScreen(navController = mockNavController, authViewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText("Crear Cuenta").assertIsDisplayed()
        composeTestRule.onNodeWithText("Nombre Completo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
        composeTestRule.onNodeWithText("Registrarse").assertIsDisplayed()
    }

    @Test
    fun registroScreen_alPresionarRegistrar_llamaAlViewModel() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<AuthViewModel>(relaxed = true)
        every {
            mockViewModel.registrar(any(), any(), any(), any())
        } answers {
            val callback = arg<(Boolean) -> Unit>(3)
            callback(true)
        }

        composeTestRule.setContent {
            RegistroScreen(navController = mockNavController, authViewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText("Nombre Completo").performTextInput("Usuario Nuevo")
        composeTestRule.onNodeWithText("Email").performTextInput("nuevo@test.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("pass123")
        composeTestRule.onNodeWithText("Registrarse").performClick()
        verify {
            mockViewModel.registrar("Usuario Nuevo", "nuevo@test.com", "pass123", any())
        }
    }
}