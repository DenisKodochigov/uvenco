package com.example.uvenco.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.uvenco.navigation.NavHostApp
import com.example.uvenco.ui.theme.UvencoTheme
import com.example.uvenco.ui.view_component.TopBarApp

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("RememberReturnType", "UnrememberedMutableState", "SuspiciousIndentation",
    "RestrictedApi"
)
@Composable
fun StartApp() {
    UvencoTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.semantics { testTagsAsResourceId = true },
            topBar = { TopBarApp() },
            content = { innerPadding ->
                NavHostApp(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        )
    }
}


@Preview
@Composable
fun StartAppPreview(){
    StartApp()
}