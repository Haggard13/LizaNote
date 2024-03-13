@file:OptIn(ExperimentalMaterial3Api::class)

package ru.eh13.lizanote.notes.ui.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import ru.eh13.lizanote.notes.ui.viewmodel.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import feature.notes.ui.R
import kotlinx.coroutines.launch
import ru.eh13.lizanote.notes.di.NotesScreenComponent
import ru.eh13.lizanote.notes.di.NotesScreenComponentImpl
import ru.eh13.lizanote.notes.ui.model.uiModel.PreviewNoteUiModel
import ru.eh13.lizanote.notes.ui.viewmodel.NotesUiState
import ru.eh13.lizanote.uikit.LizaNoteTheme

@Composable
fun NotesScreen() {
    val component: NotesScreenComponent = viewModel { NotesScreenComponentImpl() }
    val viewModel: NotesViewModel = viewModel { component.viewModel }

    NotesScreenContent(
        uiState = viewModel.uiState,
        onNoteClicked = remember { { viewModel.onNoteClicked(it) } },
        onAddClicked = remember { { viewModel.onAddNoteClicked() } }
    )
}

@Composable
private fun NotesScreenContent(
    uiState: NotesUiState,
    onNoteClicked: (id: Long) -> Unit = {},
    onAddClicked: () -> Unit = {}
) {
    Scaffold(topBar = { TopBar(onAddClicked) }) { paddingValues ->
        val cardContentModifier = remember { Modifier.padding(16.dp) }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            Modifier.padding(horizontal = 4.dp),
            contentPadding = paddingValues,
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(items = uiState.notes, key = { it.id }) {
                OutlinedCard({ onNoteClicked(it.id) }) {
                    Column(
                        cardContentModifier,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (it.name.isNotEmpty()) {
                            Text(
                                text = it.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        if (it.text.isNotEmpty()) {
                            Text(
                                text = it.text,
                                maxLines = 10,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    onAddClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.notes)) },
        actions = {
            IconButton(onClick = onAddClicked) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Добавить заметку")
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = "spec:parent=pixel_5,orientation=landscape"
)
@Composable
fun NotesScreenPreview() {
    LizaNoteTheme {
        NotesScreenContent(uiState = NotesUiStateMock())
    }
}

class NotesUiStateMock : NotesUiState {
    override val notes = listOf(
        PreviewNoteUiModel(
            1,
            "Огромная заметка с длинным названием",
            buildAnnotatedString {
                append(
                    "Эта очень длинная заметка должна быть больше чем все другие заметки. " +
                        "В протичном случае она здесь как бы и не нужна, потому что она не выполняет свои прямые функции." +
                        "Нужно сделать заметку еще более длинной"
                )
                addStyle(SpanStyle(color = Color.Green), 0, 8)
                addStyle(SpanStyle(background = Color.Green), 10, 20)
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline), 22, 32)
                addStyle(SpanStyle(textDecoration = TextDecoration.LineThrough), 34, 44)
                addStyle(SpanStyle(fontStyle = FontStyle.Italic), 46, 56)
                addStyle(SpanStyle(fontWeight = FontWeight.Bold), 58, 68)
            }
        ),
        PreviewNoteUiModel(
            2,
            "Длинная заметка",
            AnnotatedString("Тут должна быть просто длинная замекта, Не самая длинная")
        ),
        PreviewNoteUiModel(3, "Карлик", AnnotatedString("А тут коротыщ")),
    )
}