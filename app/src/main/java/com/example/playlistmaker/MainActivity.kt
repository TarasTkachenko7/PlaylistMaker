package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playlistmaker.ui.theme.BlackColor
import com.example.playlistmaker.ui.theme.BlueColor
import com.example.playlistmaker.ui.theme.GreyColor
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme

enum class MenuScreenTarget {
    SEARCH, PLAYLISTS, FAVORITES, SETTINGS
}

enum class MenuItems(
    val icon: ImageVector,
    @StringRes val title: Int,
    val target: MenuScreenTarget
) {
    SEARCH(Icons.Default.Search, R.string.search, MenuScreenTarget.SEARCH),
    PLAYLISTS(Icons.Default.LibraryMusic, R.string.playlists, MenuScreenTarget.PLAYLISTS),
    FAVORITES(Icons.Default.FavoriteBorder, R.string.favorites, MenuScreenTarget.FAVORITES),
    SETTINGS(Icons.Default.Settings, R.string.settings, MenuScreenTarget.SETTINGS)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaylistMakerTheme {
                MainScreen(
                    onNavigateTo = { target -> handleNavigation(target) }
                )
            }
        }
    }

    private fun handleNavigation(target: MenuScreenTarget) {
        when (target) {
            MenuScreenTarget.SETTINGS -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
            MenuScreenTarget.SEARCH -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            MenuScreenTarget.PLAYLISTS -> { /* На будущее */ }
            MenuScreenTarget.FAVORITES -> { /* На будущее */ }
        }
    }
}

@Composable
fun MainScreen(
    onNavigateTo: (MenuScreenTarget) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BlueColor)
    ) {
        Column(modifier = Modifier.statusBarsPadding()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.playlist_maker),
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.padding(16.dp)
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                ) {
                    MenuItems.entries.forEach { item ->
                        MenuItem(
                            icon = item.icon,
                            title = stringResource(item.title),
                            onClick = { onNavigateTo(item.target) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = BlackColor
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                color = BlackColor,
                fontWeight = FontWeight.W500,
                letterSpacing = 0.5.sp
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = GreyColor
        )
    }
}