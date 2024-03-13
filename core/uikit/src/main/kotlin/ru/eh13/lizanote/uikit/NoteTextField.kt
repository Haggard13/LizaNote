package ru.eh13.lizanote.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun EditorTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    textStyle: TextStyle = LocalTextStyle.current
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = LocalContentColor.current))

    BasicTextField(
        value,
        onValueChanged,
        modifier,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun EditorTextField(
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    textStyle: TextStyle = LocalTextStyle.current
) {

    val mergedTextStyle = textStyle.merge(TextStyle(color = LocalContentColor.current))

    BasicTextField(
        value,
        onValueChanged,
        modifier,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
    )
}