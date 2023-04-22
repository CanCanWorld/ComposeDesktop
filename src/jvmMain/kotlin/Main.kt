import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import page.ChapterPage
import page.HomePage
import page.PlayerPage
import route.Route.*

@Suppress("UNUSED_EXPRESSION")
fun main() = application {

    val windowState = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "title",
        state = windowState,
    ) {
        var route by remember {
            mutableStateOf(Home)
        }

        MaterialTheme {
            when (route) {
                Home -> {
                    HomePage(windowState) {
                        route = Chapter
                    }
                }

                Chapter -> {
                    ChapterPage(windowState, {
                        route = Home
                    }, {
                        route = Player
                    })
                }

                Player -> {
                    PlayerPage {
                        route = Chapter
                    }
                }
            }
        }
    }
}
