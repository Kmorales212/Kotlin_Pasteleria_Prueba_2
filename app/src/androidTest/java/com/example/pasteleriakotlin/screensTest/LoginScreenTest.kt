package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import com.example.pasteleriakotlin.ui.viewModel.AuthViewModel
import com.example.pasteleriakotlin.ui.screens.LoginScreen
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_muestraElementosCorrectamente() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<AuthViewModel>(relaxed = true)

        composeTestRule.setContent {
            LoginScreen(navController = mockNavController, authViewModel = mockViewModel)
        }
        composeTestRule.onNodeWithText("Iniciar Sesión").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ingresar").assertIsDisplayed()
    }

    @Test
    fun loginScreen_alPresionarIngresar_llamaAlViewModel() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<AuthViewModel>(relaxed = true)

        every {
            mockViewModel.login(any(), any(), any())
        } answers {
            val callback = arg<(Boolean) -> Unit>(2)
            callback(true)
        }

        composeTestRule.setContent {
            LoginScreen(navController = mockNavController, authViewModel = mockViewModel)
        }


        composeTestRule.onNodeWithText("Email").performTextInput("test@prueba.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("123456")
        composeTestRule.onNodeWithText("Ingresar").performClick()
        verify {
            mockViewModel.login("test@prueba.com", "123456", any())
        }
    }
}