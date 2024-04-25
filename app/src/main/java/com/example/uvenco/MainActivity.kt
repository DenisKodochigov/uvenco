package com.example.uvenco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uvenco.service.TimeProvider
import com.example.uvenco.ui.StartApp
import com.example.uvenco.ui.theme.UvencoTheme
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