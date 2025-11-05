package com.example.pasteleriakotlin.datos

import com.example.pasteleriakotlin.R

object DatosEjemplo {

    val catalogoProductos = listOf(
        Producto(
            id = 1,
            nombre = "Pastel de Chocolate",
            precio = 15000.0,
            imagen = R.drawable.pastel_chocolate
        ),
        Producto(
            id = 2,
            nombre = "Pastel de Fresa",
            precio = 17000.0,
            imagen = R.drawable.pastel_fresa
        ),
        Producto(
            id = 3,
            nombre = "Torta de Mil Hojas",
            precio = 18000.0,
            imagen = R.drawable.torta_milhoja
        ),
        Producto(
            id = 4,
            nombre = "Kuchen de Manzana",
            precio = 12000.0,
            imagen = R.drawable.kuchen_de_manzana
        ),
        Producto(
            id = 5,
            nombre = "Torta Selva Negra",
            precio = 20000.0,
            imagen = R.drawable.selva_negra
        )
    )
}