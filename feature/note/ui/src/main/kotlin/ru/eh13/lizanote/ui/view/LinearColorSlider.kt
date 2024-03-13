package ru.eh13.lizanote.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import ru.eh13.lizanote.ui.model.state.HsvColorSelectorState
import kotlin.math.pow

@Stable
private fun Color.toHexString(): String {
    return "#" + (
        (alpha * 255 * 16.0.pow(6)) +
            (red * 255 * 16.0.pow(4)) +
            (green * 255 * 16.0.pow(2)) +
            (blue * 255)
        ).toHexString()
}

@Stable
private fun Double.toHexString() = Integer.toHexString(this.toInt())

@Stable
private fun Float.toHexString() = Integer.toHexString(this.toInt())

@Composable
fun Tile(
    state: HsvColorSelectorState,
    modifier: Modifier = Modifier
) {
    val background = Modifier.drawBehind { drawRect(state.color.rgbColor) }
    Box(
        modifier.then(background),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = state.color.rgbColor.toHexString(),
            color = contentColorFor(backgroundColor = state.color.rgbColor)
        )
    }
}

@Composable
fun HueColorSlider(
    state: HsvColorSelectorState,
    modifier: Modifier = Modifier,
) {
    LinearSlider(
        initialValue = remember { state.color.hue },
        brush = { Brush.hsvHorizontalGradient() },
        onValueChanged = { state.setHue(it) },
        modifier,
    )
}

@Composable
fun BrightnessColorSlider(
    state: HsvColorSelectorState,
    modifier: Modifier = Modifier,
) {
    LinearSlider(
        initialValue = remember { state.color.brightness },
        brush = {
            Brush.horizontalGradient(
                colors = listOf(
                    Color.Black,
                    state.color.copy(brightness = 1F).rgbColor
                )
            )
        },
        onValueChanged = { state.setBrightness(it) },
        modifier
    )
}

@Composable
fun AlphaColorSlider(
    state: HsvColorSelectorState,
    modifier: Modifier = Modifier,
) {
    LinearSlider(
        initialValue = remember { state.color.alpha },
        brush = {
            Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    state.color.rgbColor.copy(alpha = 1F)
                )
            )
        },
        onValueChanged = { state.setAlpha(it) },
        modifier
    )
}

@Composable
private fun LinearSlider(
    initialValue: Float,
    brush: () -> Brush,
    onValueChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier) {
        val parentWidth = this.constraints.maxWidth
        val parentHeight = this.constraints.maxHeight
        val padding = parentHeight / 2F
        val positionRange = (padding)..(parentWidth - padding)
        val positionsCount = positionRange.endInclusive - positionRange.start

        var selectorPosition by remember { mutableFloatStateOf(initialValue + padding) }

        val pointerInputTap = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    selectorPosition = it.x.coerceIn(positionRange)
                    onValueChanged((selectorPosition - padding) / positionsCount)
                }
            )
        }
        val pointerInputDrag = Modifier.pointerInput(Unit) {
            detectHorizontalDragGestures { change, dragAmount ->
                change.consume()
                selectorPosition = (selectorPosition + dragAmount).coerceIn(positionRange)
                onValueChanged((selectorPosition - padding) / positionsCount)
            }
        }

        val draw = Modifier.drawBehind {
            val radius = padding - 4.dp.toPx()
            drawRoundRect(
                brush = brush(),
                topLeft = Offset(padding, 0F),
                size = Size(
                    width = size.width - padding * 2,
                    height = size.height
                ),
                cornerRadius = CornerRadius(padding)
            )
            drawCircle(
                Color.White,
                radius = radius,
                center = center.copy(x = selectorPosition)
            )
        }

        Box(
            Modifier
                .fillMaxSize()
                .then(pointerInputTap)
                .then(pointerInputDrag)
                .then(draw)
        )
    }
}