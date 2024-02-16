package com.example.design_system

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppStatusBarColored(
    useDarkTheme: Boolean,
    color: Color = Color.Magenta
) {
    val view = LocalView.current
    val context = view.context

    if (context is Activity) {
        SideEffect {
            val window = context.window
            window.statusBarColor = color.toArgb()
            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = useDarkTheme
        }
    }
}