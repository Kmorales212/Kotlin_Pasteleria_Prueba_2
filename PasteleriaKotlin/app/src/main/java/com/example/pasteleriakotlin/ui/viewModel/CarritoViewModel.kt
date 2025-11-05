package com.example.pasteleriakotlin.ui.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf // Importante
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pasteleriakotlin.datos.ItemCarrito
import com.example.pasteleriakotlin.datos.Producto
import kotlin.collections.find
import kotlin.collections.sumOf
import kotlin.collections.toMutableList

class CarritoViewModel : ViewModel() {


    private val _items = mutableStateOf<List<ItemCarrito>>(emptyList())


    val items: State<List<ItemCarrito>> = _items


    val total: State<Double> = derivedStateOf {
        _items.value.sumOf { it.producto.precio * it.cantidad }
    }


    fun agregarAlCarrito(producto: Producto, cantidad: Int) {

        if (cantidad <= 0) return

        val listaActual = _items.value.toMutableList()

        val itemExistente = listaActual.find { it.producto.id == producto.id }

        if (itemExistente != null) {

            val nuevaCantidad = itemExistente.cantidad + cantidad
            val itemActualizado = itemExistente.copy(cantidad = nuevaCantidad)


            val indice = listaActual.indexOf(itemExistente)
            listaActual[indice] = itemActualizado
        } else {

            listaActual.add(ItemCarrito(producto = producto, cantidad = cantidad))
        }


        _items.value = listaActual
    }


    fun eliminarDelCarrito(item: ItemCarrito) {
        val listaActual = _items.value.toMutableList()
        listaActual.remove(item)
        _items.value = listaActual
    }


    fun limpiarCarrito() {
        _items.value = emptyList()
    }
}