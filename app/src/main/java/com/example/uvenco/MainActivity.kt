package com.example.uvenco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.uvenco.service.TimeProvider
import com.example.uvenco.ui.StartApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TimeProvider.startEngine()
        setContent { StartApp() }
    }

    override fun onStop() {
        super.onStop()
        TimeProvider.stopEngine()
    }
}