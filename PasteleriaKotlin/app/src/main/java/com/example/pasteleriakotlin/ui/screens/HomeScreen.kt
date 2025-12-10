package com.example.pasteleriakotlin.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pasteleriakotlin.navegacion.RUTA_CARRITO
import com.example.pasteleriakotlin.navegacion.RUTA_CATALOGO
import com.example.pasteleriakotlin.navegacion.RUTA_CONTACTO
import com.example.pasteleriakotlin.navegacion.RUTA_HOME
import com.example.pasteleriakotlin.navegacion.RUTA_LOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val haptic = LocalHapticFeedback.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pastelería Kotlin") },
                actions = {
                    IconButton(onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)

                        navController.navigate(RUTA_LOGIN) {
                            popUpTo(0) { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar Sesión"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = RUTA_HOME)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "¡Bienvenidos a Pastelería Kotlin!",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
                )
                Text("Somos una pastelería dedicada a crear los momentos más dulces.")
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String) {
    val haptic = LocalHapticFeedback.current

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = (currentRoute == RUTA_HOME),
            onClick = {
                if (currentRoute != RUTA_HOME) {
                    // VIBRAR AL CAMBIAR
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    navController.navigate(RUTA_HOME) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "Catálogo") },
            label = { Text("Catálogo") },
            selected = (currentRoute == RUTA_CATALOGO),
            onClick = {
                if (currentRoute != RUTA_CATALOGO) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    navController.navigate(RUTA_CATALOGO) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") },
            selected = (currentRoute == RUTA_CARRITO),
            onClick = {
                if (currentRoute != RUTA_CARRITO) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    navController.navigate(RUTA_CARRITO) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Contacto") },
            label = { Text("Contacto") },
            selected = (currentRoute == RUTA_CONTACTO),
            onClick = {
                if (currentRoute != RUTA_CONTACTO) {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    navController.navigate(RUTA_CONTACTO) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}