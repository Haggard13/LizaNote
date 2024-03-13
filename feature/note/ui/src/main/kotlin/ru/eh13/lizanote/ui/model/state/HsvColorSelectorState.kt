package ru.eh13.lizanote.ui.model.state

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import ru.eh13.lizanote.ui.model.uiModel.HsvColor

@Composable
fun rememberHsvColorSelectorState(
    initialColor: HsvColor,
    onValueChanged: (HsvColor) -> Unit
) = remember { HsvColorSelectorState(initialColor, onValueChanged) }

@Stable
class HsvColorSelectorState(
    initialColor: HsvColor,
    private val onValueChanged: (HsvColor) -> Unit
) {

    var color by mutableStateOf(initialColor)
        private set

    init {
        updateColor()
    }

    fun setHue(hueInPercent: Float) = updateColor(hue = hueInPercent * 360)

    fun setBrightness(brightness: Float) = updateColor(brightness = brightness)

    fun setAlpha(alpha: Float) = updateColor(alpha = alpha)

    private fun updateColor(
        hue: Float = color.hue,
        brightness: Float = color.brightness,
        alpha: Float = color.alpha
    ) {
        this.color = color.copy(hue = hue, brightness = brightness, alpha = alpha)
        onValueChanged(color)
    }
}