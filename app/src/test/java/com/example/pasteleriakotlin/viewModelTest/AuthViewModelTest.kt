package com.example.pasteleriakotlin.viewModelTest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pasteleriakotlin.datos.Usuario
import com.example.pasteleriakotlin.ui.viewModel.AuthViewModel
import com.example.pasteleriakotlin.network.ApiService
import com.example.pasteleriakotlin.network.RetrofitClient
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals // <-- Agregado para verificar el email
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AuthViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var apiServiceMock: ApiService
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0

        apiServiceMock = mockk()
        mockkObject(RetrofitClient)
        coEvery { RetrofitClient.apiService } returns apiServiceMock

        viewModel = AuthViewModel()
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }

    @Test
    fun `registrar debe retornar true cuando la API responde correctamente`() = runTest {
        val usuarioSimulado = Usuario(id = 1, nombre = "Test", email = "test@test.com", contrasena = "123")
        coEvery { apiServiceMock.registrarUsuario(any()) } returns usuarioSimulado

        var resultado: Boolean? = null
        viewModel.registrar("Test", "test@test.com", "123") { exito ->
            resultado = exito
        }

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue("El registro debería ser exitoso", resultado == true)
        coVerify(exactly = 1) { apiServiceMock.registrarUsuario(any()) }
    }

    @Test
    fun `registrar debe retornar false cuando la API falla`() = runTest {
        coEvery { apiServiceMock.registrarUsuario(any()) } throws Exception("Error de conexión")

        var resultado: Boolean? = null
        viewModel.registrar("Test", "test@test.com", "123") { exito ->
            resultado = exito
        }

        testDispatcher.scheduler.advanceUntilIdle()

        assertFalse("El registro debería fallar ante una excepción", resultado == true)
    }

    @Test
    fun `login debe retornar true cuando la API devuelve el usuario`() = runTest {
        val usuarioRetornado = Usuario(id = 5, nombre = "LoginUser", email = "login@test.com", contrasena = "pass")
        coEvery { apiServiceMock.login(any()) } returns usuarioRetornado

        var resultado: Boolean? = null
        viewModel.login("login@test.com", "pass") { exito ->
            resultado = exito
        }

        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue("El login debería ser exitoso", resultado == true)


        assertEquals("login@test.com", viewModel.emailUsuarioLogueado)
    }

    @Test
    fun `login debe retornar false cuando la API lanza error (credenciales invalidas)`() = runTest {
        coEvery { apiServiceMock.login(any()) } throws Exception("Credenciales incorrectas")

        var resultado: Boolean? = null
        viewModel.login("login@test.com", "wrongpass") { exito ->
            resultado = exito
        }

        testDispatcher.scheduler.advanceUntilIdle()

        assertFalse("El login debería fallar", resultado == true)
    }
}