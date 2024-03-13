package ru.eh13.lizanote.ui.model.uiModel

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class HsvColor(
    val hue: Float = 0F,
    val brightness: Float = 0F,
    val saturation: Float = 1 - brightness,
    val alpha: Float = 1F
) {
    val rgbColor get() = Color.hsv(
        hue = hue,
        saturation = saturation,
        value = brightness,
        alpha = alpha
    )
}