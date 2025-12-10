package com.example.pasteleriakotlin.networkTest

import com.example.pasteleriakotlin.datos.Usuario
import com.example.pasteleriakotlin.network.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `login envia los datos correctos y parsea la respuesta`() = runTest {
        val jsonRespuesta = """
            {
                "id": 1,
                "nombre": "Usuario Mock",
                "email": "test@test.com",
                "password": "123"
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(jsonRespuesta).setResponseCode(200))

        val usuarioEnviado = Usuario(email = "test@test.com", contrasena = "123")
        val respuesta = apiService.login(usuarioEnviado)

        assertEquals("Usuario Mock", respuesta.nombre)
        assertEquals("test@test.com", respuesta.email)

        val peticionRegistrada = mockWebServer.takeRequest()
        assertEquals("POST", peticionRegistrada.method)
        assertEquals("/usuarios/login", peticionRegistrada.path)

        val cuerpoEsperado = Gson().toJson(usuarioEnviado)
        assertEquals(cuerpoEsperado, peticionRegistrada.body.readUtf8())
    }
}