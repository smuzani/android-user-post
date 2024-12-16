package com.muz.designSystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val FlowardColorScheme = lightColorScheme(
  primary = Color(0xFF074E59),
  onPrimary = Color.White,
  surface = Color(0xFFF7F6F2),
  background = Color(0xFFF7F6F2),
  surfaceVariant = Color(0xFFECEBE6),
  onSurface = Color(0xFF074E59),
  onBackground = Color(0xFF074E59),
  onSurfaceVariant = Color(0xFF000000)
)

private val FlowardTypography = androidx.compose.material3.Typography(
  bodyLarge = TextStyle(
    color = Color(0xFF074E59),
    fontSize = 14.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
  ),
  bodyMedium = TextStyle(
    color = Color(0xFF000000),
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
  ),
  titleLarge = TextStyle(
    color = Color(0xFF074E59),
    fontSize = 18.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp,
    fontWeight = FontWeight.SemiBold
  ),
  headlineLarge = TextStyle(
    color = Color(0xFF074E59),
    fontSize = 40.sp,
    lineHeight = 48.sp,
    letterSpacing = 0.sp,
    fontWeight = FontWeight.Bold
  ),
  headlineMedium = TextStyle(
    color = Color(0xFF074E59),
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp,
    fontWeight = FontWeight.SemiBold
  )
)

@Composable
fun UserPostTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = FlowardColorScheme,
    typography = FlowardTypography,
    content = content
  )
}