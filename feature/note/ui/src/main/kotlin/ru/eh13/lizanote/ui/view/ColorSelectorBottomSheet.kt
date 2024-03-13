package ru.eh13.lizanote.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import core.uikit.R
import ru.eh13.lizanote.ui.model.state.rememberHsvColorSelectorState
import ru.eh13.lizanote.ui.model.uiModel.HsvColor
import ru.eh13.lizanote.uikit.LizaNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSelectorBottomSheet(
    initialColor: HsvColor,
    onDismissColorSelectorRequested: () -> Unit,
    onColorSelected: (HsvColor) -> Unit,
    onApplyButtonClicked: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = { onDismissColorSelectorRequested() },
        sheetState = sheetState
    ) {
        ColorSelectorContent(initialColor, onColorSelected, onApplyButtonClicked)
    }
}

@Composable
private fun ColumnScope.ColorSelectorContent(
    initialColor: HsvColor = HsvColor(),
    onColorSelected: (HsvColor) -> Unit = {},
    onApplyButtonClicked: () -> Unit = {}
) {
    val colorState = rememberHsvColorSelectorState(initialColor, onColorSelected)
    Tile(
        state = colorState,
        Modifier
            .padding(bottom = 16.dp)
            .size(100.dp, 30.dp)
            .clip(MaterialTheme.shapes.medium)
            .align(Alignment.CenterHorizontally)
    )
    HueColorSlider(
        colorState,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(32.dp)

    )
    BrightnessColorSlider(
        colorState,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(32.dp)
    )
    AlphaColorSlider(
        colorState,
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(32.dp)
    )
    OutlinedButton(
        onClick = { onApplyButtonClicked() },
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(text = stringResource(id = R.string.apply))
    }
    Spacer(Modifier.height(32.dp))
}

@Preview()
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440,orientation=landscape"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_5,orientation=landscape"
)
@Composable
fun ColorSelectorBottomSheetPreview() {
    LizaNoteTheme() {
        Column() {
            ColorSelectorContent()
        }
    }
}