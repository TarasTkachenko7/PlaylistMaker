package com.example.playlistmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmaker.ui.theme.BlackColor
import com.example.playlistmaker.ui.theme.GreyColor
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme

enum class SettingsAction {
    TOGGLE_THEME, SHARE_APP, CONTACT_SUPPORT, OPEN_AGREEMENT
}

enum class SettingsItems(
    val icon: ImageVector,
    @StringRes val title: Int,
    val action: SettingsAction
) {
    THEME(Icons.Default.ToggleOff, R.string.black_theme, SettingsAction.TOGGLE_THEME),
    SHARE(Icons.Default.Share, R.string.share_app, SettingsAction.SHARE_APP),
    SUPPORT(Icons.Default.SupportAgent, R.string.support, SettingsAction.CONTACT_SUPPORT),
    AGREE(Icons.Default.ChevronRight, R.string.agree, SettingsAction.OPEN_AGREEMENT)
}

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerTheme {
                SettingsScreen(
                    onBackClick = { finish() },
                    onActionClick = { action -> handleSettingsAction(action) }
                )
            }
        }
    }

    private fun handleSettingsAction(action: SettingsAction) {
        when (action) {
            SettingsAction.TOGGLE_THEME -> {
                // TODO: Переключить тему (реализуешь на следующих уроках)
            }
            SettingsAction.SHARE_APP -> {
                // TODO: Запустить Интент "Поделиться"
            }
            SettingsAction.CONTACT_SUPPORT -> {
                // TODO: Запустить Интент отправки письма в техподдержку
            }
            SettingsAction.OPEN_AGREEMENT -> {
                // TODO: Открыть Интент со ссылкой на пользовательское соглашение в браузере
            }
        }
    }
}

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onActionClick: (SettingsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = BlackColor,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(onClick = onBackClick)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = stringResource(R.string.settings),
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = BlackColor,
                        fontWeight = FontWeight.W500,
                        letterSpacing = 0.5.sp
                    )
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp)
            ) {
                SettingsItems.entries.forEach { item ->
                    SettingsItem(
                        title = stringResource(item.title),
                        icon = item.icon,
                        onClick = { onActionClick(item.action) }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                color = BlackColor,
                fontWeight = FontWeight.W400,
            )
        )

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = GreyColor,
            modifier = Modifier.size(24.dp)
        )
    }
}