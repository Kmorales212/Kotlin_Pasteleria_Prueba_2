package com.example.pasteleriakotlin.screensTest

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import com.example.pasteleriakotlin.datos.Producto
import com.example.pasteleriakotlin.network.ApiService
import com.example.pasteleriakotlin.network.RetrofitClient
import com.example.pasteleriakotlin.ui.screens.CatalogoScreen
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatalogoScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        // Mockeamos el Singleton RetrofitClient para interceptar la llamada a la API
        mockkObject(RetrofitClient)
    }

    @After
    fun tearDown() {
        // Limpiamos el mock después del test
        unmockkObject(RetrofitClient)
    }

    @Test
    fun catalogoScreen_muestraProductosYAgregaAlCarrito() {
        val mockNavController = mockk<NavController>(relaxed = true)
        val mockViewModel = mockk<CarritoViewModel>(relaxed = true)

        // Necesitamos mockear también el servicio de la API que usa RetrofitClient
        val mockApiService = mockk<ApiService>()

        // 1. Definimos el producto esperado
        val primerProducto = Producto(
            id = 1,
            nombre = "Pastel de Prueba",
            precio = 1000.0,
            imagenNombre = "pastel_chocolate"
        )


        coEvery { RetrofitClient.apiService.obtenerProductos() } returns listOf(primerProducto)

        composeTestRule.setContent {
            CatalogoScreen(navController = mockNavController, carritoViewModel = mockViewModel)
        }

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithText("Añadir")
                .fetchSemanticsNodes().isNotEmpty()
        }

        val botonAnadir = composeTestRule.onAllNodesWithText("Añadir").onFirst()
        botonAnadir.performClick()

        verify {
            mockViewModel.agregarAlCarrito(any(), 1)
        }
    }
}