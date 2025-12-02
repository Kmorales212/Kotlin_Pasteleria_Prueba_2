package com.example.pasteleriakotlin.screensTest

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.example.pasteleriakotlin.datos.ItemCarrito
import com.example.pasteleriakotlin.datos.Producto
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import com.example.pasteleriakotlin.ui.screens.BoletaScreen
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class BoletaScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun boletaScreen_muestraResumenFinal() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<CarritoViewModel>(relaxed = true)

        val producto = Producto(1, "Torta Final", 20000.0, 0)
        val listaItems = listOf(ItemCarrito(producto, 1))

        every { mockViewModel.items } returns mutableStateOf(listaItems)
        every { mockViewModel.total } returns mutableStateOf(20000.0)

        composeTestRule.setContent {
            BoletaScreen(navController = mockNavController, carritoViewModel = mockViewModel)
        }
        composeTestRule.onNodeWithText("Resumen de Compra").assertIsDisplayed()
        composeTestRule.onNodeWithText("¡Gracias por tu compra!").assertIsDisplayed()
        composeTestRule.onNodeWithText("1 x Torta Final").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("$20000").onFirst().assertIsDisplayed()
        composeTestRule.onNodeWithText("Finalizar y Volver al Inicio").assertIsDisplayed()
    }
}