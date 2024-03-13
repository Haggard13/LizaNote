package ru.eh13.lizanote.ui.view

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import feature.note.ui.R
import ru.eh13.lizanote.core.viewmodel.onBackClicked
import ru.eh13.lizanote.ui.di.NoteScreenComponentImpl
import ru.eh13.lizanote.ui.model.state.rememberHsvColorSelectorState
import ru.eh13.lizanote.ui.model.uiModel.HsvColor
import ru.eh13.lizanote.ui.viewmodel.*
import ru.eh13.lizanote.uikit.LizaNoteTheme
import ru.eh13.lizanote.uikit.EditorTextField

@Composable
fun NoteScreen(args: Long?) {
    val component = viewModel { NoteScreenComponentImpl() }
    val viewModel = viewModel { component.viewModel(args) }

    NoteScreenContent(
        state = viewModel.uiState,
        onTextChanged = remember { { viewModel.onTextChanged(it) } },
        onTitleChanged = remember { { viewModel.onTitleChanged(it) } },
        onBoldSpanClicked = remember { { viewModel.onBoldSpanClicked() } },
        onItalicSpanClicked = remember { { viewModel.onItalicSpanClicked() } },
        onUnderlineSpanClicked = remember { { viewModel.onUnderlineSpanClicked() } },
        onStrikethroughSpanClicked = remember { { viewModel.onStrikethroughSpanClicked() } },
        onColorSpanClicked = remember { { viewModel.onColorSpanClicked() } },
        onBackgroundSpanClicked = remember { { viewModel.onBackgroundSpanClicked() } },
        onResetColorSpanClicked = remember { { viewModel.onResetColorSpanClicked() } },
        onClearSpansClicked = remember { { viewModel.onClearSpansClicked() } },
        onColorSelected = remember { { viewModel.onColorSelected(it) } },
        onBackClicked = remember { { viewModel.onBackClicked() } },
        onDismissColorSelectorRequested = remember { { viewModel.onDismissColorSelectorRequested() } },
        onApplyButtonClicked = remember { { viewModel.onApplyButtonClicked() } },
        onSelectColorClicked = remember { { viewModel.onSelectColorClicked() } }
    )
}

@Composable
private fun NoteScreenContent(
    state: NoteUiState,
    onTextChanged: (TextFieldValue) -> Unit = {},
    onTitleChanged: (String) -> Unit = {},
    onBoldSpanClicked: () -> Unit = {},
    onItalicSpanClicked: () -> Unit = {},
    onUnderlineSpanClicked: () -> Unit = {},
    onStrikethroughSpanClicked: () -> Unit = {},
    onColorSpanClicked: () -> Unit = {},
    onBackgroundSpanClicked: () -> Unit = {},
    onResetColorSpanClicked: () -> Unit = {},
    onClearSpansClicked: () -> Unit = {},
    onColorSelected: (HsvColor) -> Unit = {},
    onBackClicked: () -> Unit = {},
    onDismissColorSelectorRequested: () -> Unit = {},
    onApplyButtonClicked: () -> Unit = {},
    onSelectColorClicked: () -> Unit = {},
) {

    BackHandler { onBackClicked() }

    Scaffold(
        topBar = { TopBar(onBackClicked) },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.add(WindowInsets.ime)
    ) { paddings ->

        if (state.colorSelectorIsVisible) {
            val initialColor = remember { state.color }
            ColorSelectorBottomSheet(
                initialColor,
                onDismissColorSelectorRequested,
                onColorSelected,
                onApplyButtonClicked
            )
        }

        Column(
            Modifier
                .padding(paddings)
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            EditorTextField(
                value = state.title,
                onValueChanged = onTitleChanged,
                Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleLarge,
            )

            HorizontalDivider(Modifier.fillMaxWidth().padding(8.dp))

            EditorTextField(
                value = state.textFieldValue,
                onValueChanged = { newValue ->
                    if (newValue != state.textFieldValue.copy(AnnotatedString(state.textFieldValue.text))) {
                        onTextChanged(newValue)
                    }
                },
                Modifier
                    .fillMaxWidth()
                    .weight(1F)
            )

            Row(
                Modifier
                    .scrollable(
                        rememberScrollableState { it },
                        Orientation.Horizontal
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBoldSpanClicked) {
                    Icon(Icons.Default.FormatBold, contentDescription = "")
                }
                IconButton(onClick = onItalicSpanClicked) {
                    Icon(Icons.Default.FormatItalic, contentDescription = "")
                }
                VerticalDivider(Modifier.sizeIn(maxHeight = 24.dp))
                IconButton(onClick = onUnderlineSpanClicked) {
                    Icon(Icons.Default.FormatUnderlined, contentDescription = "")
                }
                IconButton(onClick = onStrikethroughSpanClicked) {
                    Icon(Icons.Default.FormatStrikethrough, contentDescription = "")
                }
                VerticalDivider(Modifier.sizeIn(maxHeight = 24.dp))
                IconButton(onClick = onColorSpanClicked) {
                    Icon(Icons.Default.FormatColorText, contentDescription = "")
                }
                IconButton(onClick = onBackgroundSpanClicked) {
                    Icon(Icons.Default.FormatColorFill, contentDescription = "")
                }
                IconButton(onClick = onResetColorSpanClicked) {
                    Icon(Icons.Default.FormatColorReset, contentDescription = "")
                }
                IconButton(onClick = onSelectColorClicked) {
                    val borderColor = MaterialTheme.colorScheme.onBackground
                    Canvas(modifier = Modifier.size(24.dp)) {
                        drawCircle(
                            color = borderColor,
                            style = Stroke(width = 2.dp.toPx()),
                        )
                        drawCircle(
                            color = state.color.rgbColor,
                            radius = (size.minDimension / 2) - 4.dp.toPx()
                        )
                    }
                }
                VerticalDivider(Modifier.sizeIn(maxHeight = 24.dp))
                IconButton(onClick = { onClearSpansClicked() }) {
                    Icon(Icons.Default.FormatClear, contentDescription = "")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.note)) },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Назад")
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(
    showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440,orientation=landscape"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_5,orientation=landscape"
)
@Composable
fun NoteScreenPreview() {
    LizaNoteTheme() {
        NoteScreenContent(mockState())
    }
}

private fun mockState(): NoteUiState {
    return object : NoteUiState {
        override val textFieldValue =
            TextFieldValue(
                buildAnnotatedString {
                    append(
                        "Эта очень длинная заметка должна быть больше чем все другие заметки. " +
                            "В протичном случае она здесь как бы и не нужна, потому что она не выполняет свои прямые функции." +
                            "Нужно сделать заметку еще более длинной"
                    )
                    addStyle(SpanStyle(color = Color.Green), 0, 9)
                    addStyle(SpanStyle(background = Color.Green), 10, 17)
                    addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 26, 32)
                    addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 33, 44)
                    addStyle(SpanStyle(fontStyle = FontStyle.Italic), 45, 52)
                    addStyle(SpanStyle(fontWeight = FontWeight.Bold), 60, 68)
                }
            )

        override val title: String = "Огромная заметка с длинным названием"

        override val colorSelectorIsVisible: Boolean = false
        override val color: HsvColor = HsvColor()
    }
}