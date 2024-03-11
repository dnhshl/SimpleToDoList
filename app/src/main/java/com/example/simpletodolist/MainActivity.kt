package com.example.simpletodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.simpletodolist.ui.screens.MyApp
import com.example.simpletodolist.ui.theme.SimpleToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SimpleToDoListTheme {
                MyApp()
            }
        }
    }
}





