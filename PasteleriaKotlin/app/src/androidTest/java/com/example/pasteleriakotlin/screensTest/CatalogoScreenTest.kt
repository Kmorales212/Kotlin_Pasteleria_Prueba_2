package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import com.example.pasteleriakotlin.datos.DatosEjemplo
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import com.example.pasteleriakotlin.ui.screens.CatalogoScreen
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class CatalogoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun catalogoScreen_muestraProductosYAgregaAlCarrito() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<CarritoViewModel>(relaxed = true)
        val primerProducto = DatosEjemplo.catalogoProductos[0]

        composeTestRule.setContent {
            CatalogoScreen(navController = mockNavController, carritoViewModel = mockViewModel)
        }

        composeTestRule.onNodeWithText(primerProducto.nombre).assertIsDisplayed()


        val botonAnadir = composeTestRule.onAllNodesWithText("Añadir").onFirst()
        botonAnadir.performClick()
        verify {
            mockViewModel.agregarAlCarrito(primerProducto, 1)
        }
    }
}