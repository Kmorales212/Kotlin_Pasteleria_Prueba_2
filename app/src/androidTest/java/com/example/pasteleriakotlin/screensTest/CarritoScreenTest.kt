package com.example.pasteleriakotlin.screensTest

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.example.pasteleriakotlin.datos.ItemCarrito
import com.example.pasteleriakotlin.datos.Producto
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import com.example.pasteleriakotlin.ui.screens.CarritoScreen
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class CarritoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun carritoScreen_muestraProductosYTotal() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<CarritoViewModel>(relaxed = true)


        val productoPrueba = Producto(
            id = 1,
            nombre = "Pastel Test",
            precio = 5000.0,
            imagenNombre = "pastel_test"
        )
        val listaItems = listOf(ItemCarrito(productoPrueba, 2))

        every { mockViewModel.items } returns mutableStateOf(listaItems)
        every { mockViewModel.total } returns mutableStateOf(10000.0)

        composeTestRule.setContent {
            CarritoScreen(navController = mockNavController, carritoViewModel = mockViewModel)
        }


        composeTestRule.onNodeWithText("Mi Carrito").assertIsDisplayed()

        composeTestRule.onNodeWithText("Pastel Test").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cantidad: 2").assertIsDisplayed()

        composeTestRule.onNodeWithText("Subtotal: $10000").assertIsDisplayed()

        composeTestRule.onNodeWithText("Total: $10000").assertIsDisplayed()

        composeTestRule.onNodeWithText("Pagar", substring = true).assertIsDisplayed()
    }
}