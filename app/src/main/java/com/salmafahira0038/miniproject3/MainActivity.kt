package com.salmafahira0038.miniproject3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.salmafahira0038.miniproject3.ui.screen.MainScreen
import com.salmafahira0038.miniproject3.ui.theme.MiniProject3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProject3Theme {
                MainScreen()
            }
        }
    }
}

