package com.example.pasteleriakotlin.viewModelTest

import com.example.pasteleriakotlin.datos.Producto
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CarritoViewModelTest {

    private lateinit var viewModel: CarritoViewModel

    @Before
    fun setup() {
        viewModel = CarritoViewModel()
    }

    @Test
    fun `agregarAlCarrito debe agregar un producto nuevo a la lista`() {
        val producto = Producto(id = 1, nombre = "Pastel Test", precio = 1000.0, imagen = 0)

        viewModel.agregarAlCarrito(producto, 2)

        val items = viewModel.items.value
        assertEquals("Debería haber 1 item en el carrito", 1, items.size)
        assertEquals("El producto no coincide", producto, items[0].producto)
        assertEquals("La cantidad debería ser 2", 2, items[0].cantidad)
    }

    @Test
    fun `agregarAlCarrito debe sumar la cantidad si el producto ya existe`() {
        val producto = Producto(id = 1, nombre = "Pastel Test", precio = 1000.0, imagen = 0)

        viewModel.agregarAlCarrito(producto, 2)
        viewModel.agregarAlCarrito(producto, 3)

        val items = viewModel.items.value

        assertEquals("Debería seguir habiendo solo 1 item", 1, items.size)
        assertEquals("La cantidad debería haberse sumado", 5, items[0].cantidad)
    }

    @Test
    fun `calcularTotal debe sumar correctamente el precio por cantidad`() {
        val p1 = Producto(1, "P1", 100.0, 0)
        val p2 = Producto(2, "P2", 200.0, 0)

        viewModel.agregarAlCarrito(p1, 2) // 100 * 2 = 200
        viewModel.agregarAlCarrito(p2, 1) // 200 * 1 = 200

        val totalCalculado = viewModel.total.value

        assertEquals("El total calculado es incorrecto", 400.0, totalCalculado, 0.0)
    }

    @Test
    fun `eliminarDelCarrito debe quitar el item de la lista`() {
        val producto = Producto(1, "Pastel Test", 1000.0, 0)
        viewModel.agregarAlCarrito(producto, 1)

        val itemAEliminar = viewModel.items.value[0]

        viewModel.eliminarDelCarrito(itemAEliminar)

        assertTrue("El carrito debería estar vacío", viewModel.items.value.isEmpty())
    }

    @Test
    fun `limpiarCarrito debe vaciar toda la lista y el total`() {
        viewModel.agregarAlCarrito(Producto(1, "P1", 10.0, 0), 1)
        viewModel.agregarAlCarrito(Producto(2, "P2", 10.0, 0), 1)

        viewModel.limpiarCarrito()

        assertTrue("La lista debería estar vacía", viewModel.items.value.isEmpty())
        assertEquals("El total debería ser 0", 0.0, viewModel.total.value, 0.0)
    }
}