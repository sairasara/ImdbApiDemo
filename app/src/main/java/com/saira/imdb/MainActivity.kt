package com.saira.imdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.saira.imdb.navigation.AppNavigation
import com.saira.imdb.ui.theme.ImdbApiDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImdbApiDemoTheme{
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}