package com.example.pasteleriakotlin.navegacion


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import com.example.pasteleriakotlin.ui.viewModel.AuthViewModel
import com.example.pasteleriakotlin.ui.viewModel.CarritoViewModel


import com.example.pasteleriakotlin.ui.screens.LoginScreen
import com.example.pasteleriakotlin.ui.screens.RegistroScreen
import com.example.pasteleriakotlin.ui.screens.HomeScreen
import com.example.pasteleriakotlin.ui.screens.ContactoScreen
import com.example.pasteleriakotlin.ui.screens.CatalogoScreen
import com.example.pasteleriakotlin.ui.screens.CarritoScreen
import com.example.pasteleriakotlin.ui.screens.BoletaScreen


const val RUTA_LOGIN = "login"
const val RUTA_REGISTRO = "registro"
const val RUTA_HOME = "home"
const val RUTA_CATALOGO = "catalogo"
const val RUTA_CARRITO = "carrito"
const val RUTA_CONTACTO = "contacto"
const val RUTA_BOLETA = "boleta"

@Composable
fun NavegacionApp() {
    val navController = rememberNavController()


    val authViewModel: AuthViewModel = viewModel()

    val carritoViewModel: CarritoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RUTA_LOGIN
    ) {

        composable(RUTA_LOGIN) {
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(RUTA_REGISTRO) {
            RegistroScreen(navController = navController, authViewModel = authViewModel)
        }


        composable(RUTA_HOME) {
            HomeScreen(navController = navController)
        }
        composable(RUTA_CATALOGO) {

            CatalogoScreen(
                navController = navController,
                carritoViewModel = carritoViewModel
            )
        }
        composable(RUTA_CARRITO) {

            CarritoScreen(
                navController = navController,
                carritoViewModel = carritoViewModel
            )
        }
        composable(RUTA_CONTACTO) {
            ContactoScreen(navController = navController)
        }
        composable(RUTA_BOLETA) {
            BoletaScreen(
                navController = navController,
                carritoViewModel = carritoViewModel
            )
        }
    }
}
