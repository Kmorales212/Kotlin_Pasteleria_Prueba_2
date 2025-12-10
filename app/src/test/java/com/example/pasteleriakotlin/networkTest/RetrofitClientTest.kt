package com.example.pasteleriakotlin.networkTest

import com.example.pasteleriakotlin.network.RetrofitClient
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Test

class RetrofitClientTest {

    @Test
    fun `apiService se inicializa correctamente y no es nulo`() {
        val servicio = RetrofitClient.apiService

        assertNotNull("El servicio de API debería haberse creado exitosamente", servicio)
    }

    @Test
    fun `apiService es un Singleton (devuelve siempre la misma instancia)`() {
        val instancia1 = RetrofitClient.apiService
        val instancia2 = RetrofitClient.apiService

        assertSame("Debería retornar la misma instancia para optimizar recursos", instancia1, instancia2)
    }
}