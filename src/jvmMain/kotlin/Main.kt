import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import page.HomePage


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "看会片儿"
    ) {
        MaterialTheme {
            HomePage()
        }
    }
}
