package com.example.design_system

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppToolBarUiModel(
    val title: String = "",
    val toolbarColor: Color = Color.LightGray
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(
    modifier: Modifier = Modifier,
    useDarkTheme: Boolean,
    elevation: Dp = 4.dp,
    onBackPressed: (() -> Unit)? = null,
    toolBarUiModel: AppToolBarUiModel = AppToolBarUiModel()
) {
    AppStatusBarColored(
        useDarkTheme = useDarkTheme,
        color = if (useDarkTheme) Color.DarkGray else toolBarUiModel.toolbarColor
    )

    TopAppBar(
        modifier = modifier.shadow(elevation),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (useDarkTheme) Color.DarkGray else toolBarUiModel.toolbarColor,
        ),
        title = {
            Text(
                text = toolBarUiModel.title
            )

        },
        navigationIcon = {
            onBackPressed?.let {
                IconButton(
                    onClick = it,
                    modifier = Modifier
                        .semantics { contentDescription = "Press back" }
                ) {
                    Icon(
                        tint = if (useDarkTheme) Color.White else Color.DarkGray,
                        painter = painterResource(id = R.drawable.icon_button_arrow_back),
                        contentDescription = null,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun AppToolBarPreview() {
    AppToolBar(
        useDarkTheme = true,
        elevation = 4.dp,
        onBackPressed = { },
        toolBarUiModel = AppToolBarUiModel(
            title = "Title text",
        )
    )
}

@Preview
@Composable
fun AppToolBarNoArrowBackPreview() {
    AppToolBar(
        useDarkTheme = true,
        elevation = 4.dp,
        toolBarUiModel = AppToolBarUiModel(
            title = "Title text",
        )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppToolBarPreviewDark() {
    AppToolBar(
        useDarkTheme = false,
        elevation = 4.dp,
        onBackPressed = { },
        toolBarUiModel = AppToolBarUiModel(
            title = "Title text",
        )
    )
}