// ui/theme/Theme.kt
package com.app.cookbookku.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.app.cookbookku.ui.theme.Maroon

private val LightColors = lightColorScheme(
    primary = Maroon,
    onPrimary = White,
    background = LightGray,
    onBackground = Color.Black
)

@Composable
fun CookBookKuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}
