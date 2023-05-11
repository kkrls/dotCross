package com.dotcross_app.dotcross

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dotcross_app.dotcross.ui.theme.DotCrossTheme

// Main
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DotCrossTheme {
                DotCrossApp()
            }
        }
    }
}
