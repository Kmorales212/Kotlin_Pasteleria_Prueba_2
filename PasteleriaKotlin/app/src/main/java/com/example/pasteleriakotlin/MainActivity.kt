package com.example.pasteleriakotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pasteleriakotlin.navegacion.NavegacionApp
import com.example.pasteleriakotlin.ui.theme.PasteleriaKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasteleriaKotlinTheme {
                NavegacionApp()
            }
        }
    }
}